package com.pageupcomputers.apolloMicroservice.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pageupcomputers.apolloMicroservice.DTO.CustomerDTO;
import com.pageupcomputers.apolloMicroservice.DTO.CustomersDTO;
import com.pageupcomputers.apolloMicroservice.DTO.InvoiceDTO;
import com.pageupcomputers.apolloMicroservice.DTO.InvoiceLineDTO;
import com.pageupcomputers.apolloMicroservice.DTO.ItemDTO;
import com.pageupcomputers.apolloMicroservice.DTO.ItemsDTO;
import com.pageupcomputers.apolloMicroservice.DTO.OrderDTO;
import com.pageupcomputers.apolloMicroservice.DTO.OrderItemDTO;

/**
 * Service which handles all the logic regaring processing orders
 */
@Service
public class OrderService {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ItemService itemService;
    
    Logger logger = LoggerFactory.getLogger(OrderService.class);

    /**
     * Function which process a incoming order received from the RabbitMQ Queue
     * @param order
     * @return OrderDTO with the order details of the order that has been processed
     */
    public OrderDTO processOrder(OrderDTO order) {
        try {
            logger.debug(String.format("OrderDTO Contents: %s", order.toString()));

            CustomersDTO retrievedCustomers = customerService.getCustomers(order.getBillingDetails());
            logger.debug(String.format("retrievedCustomers Contents: %s", retrievedCustomers.toString()));

            Optional<CustomerDTO> resultCustomer = retrievedCustomers.getCustomers()
                    .stream()
                    .filter(customer -> customer.getAddressLine1().equals(order.getBillingDetails().getFullStreetName()))
                    .filter(customer -> customer.getPostalCode().equals(order.getBillingDetails().getZipCode()))
                    .filter(customer -> customer.getDisplayName().equals(order.getBillingDetails().getFullName()))
                    .findFirst();
            logger.debug(String.format("resultCustomer Contents: %s", resultCustomer.toString()));        

            CustomerDTO customer = null;

            if(!resultCustomer.isPresent()) {
                logger.info(String.format("Customer %s not present! Creating new customer", order.getBillingDetails().getFullName()));
                customer = customerService.createCustomer(order.getBillingDetails());
            }
            else if(retrievedCustomers.getCustomers().size() >= 2) {
                logger.warn("Caution! Multiple users found in the system, for safety reasons a new customer is made!");
                customer = customerService.createCustomer(order.getBillingDetails());
            } 
            else {
                logger.info(String.format("Customer %s present! Fetched customer details", resultCustomer.get().getDisplayName()));
                customer = resultCustomer.get();
            }

            logger.debug(String.format("customer Contents: %s", customer.toString()));

            InvoiceDTO invoice = invoiceService.createInvoice(order, customer);
            logger.debug(String.format("invoice Contents: %s", invoice.toString()));

            List<InvoiceLineDTO> invoiceLines = createInvoiceLines(order.getOrderItems(), invoice);
            logger.debug(String.format("invoiceLines Contents: %s", invoiceLines.toString()));

            logger.info(String.format("%s Invoice line(s) processed", invoiceLines.size()));

            return order;

        } 
        catch(Exception e) {
            throw e;
        }
    }

    /**
     * Function which creates the invoice lines for every order item in the order.
     * @param orderItems
     * @param invoice
     * @return List with InvoiceLineDTO which contains the invoice lines that are being added to the invoice.
     */
    private List<InvoiceLineDTO> createInvoiceLines(List<OrderItemDTO> orderItems, InvoiceDTO invoice) {
        try {
            logger.debug(String.format("orderItems Contents: %s", orderItems.toString()));
            logger.debug(String.format("invoice Contents: %s", invoice.toString()));

            List<OrderItemDTO> filteredItems = orderItems.stream()
                .filter(orderItem -> orderItem.getQuantityShipped() >= 1)
                .filter(orderItem -> orderItem.getQuantityCancelled() == 0)
                .toList();

            ItemDTO item = null;
            List<InvoiceLineDTO> invoiceLines = new ArrayList<>();

            for(OrderItemDTO orderItem : filteredItems) {
                ItemsDTO retrievedItems = itemService.getItems(orderItem);
                logger.debug(String.format("retrievedItems Contents: %s", retrievedItems.toString()));

                Optional<ItemDTO> resultItem = retrievedItems.getItems()
                    .stream()
                    .filter(newItem -> newItem.getGtin().equals(orderItem.getProduct().getEan()))
                    .findFirst();
                logger.debug(String.format("resultItem Contents: %s", resultItem.toString()));

                if(!resultItem.isPresent()) {
                    logger.info(String.format("Item %s not present! Creating new item", orderItem.getProduct().getEan()));
                    item = itemService.createItem(orderItem);
                }
                else if(retrievedItems.getItems().size() >= 2) {
                    logger.warn("Caution! Multiple items found in the system, for safety reasons a new items is made!");
                    item = itemService.createItem(orderItem);
                } 
                else {
                    logger.info(String.format("Item %s present! Fetched item details", resultItem.get().getGtin()));
                    item = resultItem.get();
                }

                logger.debug(String.format("item Contents: %s", item.toString()));

                InvoiceLineDTO invoiceLine = invoiceService.createInvoiceLine(orderItem, invoice, item);
                logger.debug(String.format("invoiceLine Contents: %s", invoiceLine.toString()));
                invoiceLines.add(invoiceLine);
            }

            return invoiceLines;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
