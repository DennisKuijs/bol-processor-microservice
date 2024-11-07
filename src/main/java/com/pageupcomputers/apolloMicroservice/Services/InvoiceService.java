package com.pageupcomputers.apolloMicroservice.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.pageupcomputers.apolloMicroservice.DTO.CustomerDTO;
import com.pageupcomputers.apolloMicroservice.DTO.InvoiceDTO;
import com.pageupcomputers.apolloMicroservice.DTO.InvoiceLineDTO;
import com.pageupcomputers.apolloMicroservice.DTO.ItemDTO;
import com.pageupcomputers.apolloMicroservice.DTO.OrderDTO;
import com.pageupcomputers.apolloMicroservice.DTO.OrderItemDTO;
import com.pageupcomputers.apolloMicroservice.Exceptions.MicrosoftBusinessCentralBadRequestException;
import kong.unirest.Unirest;

/**
 * Service which handles all the requests to the Microsoft Business Central API regaring invoices
 */
@Service
public class InvoiceService {

    @Value("${microsoft.businesscentral.tenantId}")
    private String tenantId;

    @Value("${microsoft.businesscentral.company.Id}")
    private String companyId;

    @Value("${api.url.createInvoice}")
    private String createInvoiceURL;

    @Value("${api.url.createInvoiceLine}")
    private String createInvoiceLineURL;

    @Autowired
    private MicrosoftAuthenticateService microsoftAuthenticateService;

    Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    /**
     * API Call to the Microsoft Business Central API to create a new invoice based on the customer and order details
     * @param order
     * @param customer
     * @return InvoiceDTO with the invoice details received from Microsoft Business Central.
     */
    public InvoiceDTO createInvoice(OrderDTO order, CustomerDTO customer) {

        logger.debug(String.format("OrderDTO contents: %s", order.toString()));

        microsoftAuthenticateService.checkAccessToken();

        InvoiceDTO invoice = new InvoiceDTO(order, customer);

        logger.debug(String.format("InvoiceDTO Contents: %s", invoice.toString()));

        return Unirest.post(createInvoiceURL)
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + System.getProperty("accessToken"))
        .routeParam("tenantId", tenantId)
        .routeParam("companyId", companyId)
        .body(invoice)
        .asObject(InvoiceDTO.class)
        .ifSuccess(response -> {
            logger.info(String.format("Invoice %s created succesfully", response.getBody().getNumber()));
        })
        .ifFailure(response -> {
            throw new MicrosoftBusinessCentralBadRequestException(response.getBody().toString());
        })
        .getBody();
    }

    /**
     * API Call to the Microsoft Business Central API to create a invoice line for each shipped order item.
     * @param orderItem
     * @param invoice
     * @param item
     * @return InvoiceLineDTO with the invoice line details received from the Microsoft Business Central API.
     */
    public InvoiceLineDTO createInvoiceLine(OrderItemDTO orderItem, InvoiceDTO invoice, ItemDTO item) {

        logger.debug(String.format("OrderItemDTO contents: %s", orderItem.toString()));

        microsoftAuthenticateService.checkAccessToken();

        InvoiceLineDTO invoiceLine = new InvoiceLineDTO(orderItem, item);

        logger.debug(String.format("InvoiceLineDTO Contents: %s", invoiceLine.toString()));

        return Unirest.post(createInvoiceLineURL)
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + System.getProperty("accessToken"))
        .routeParam("tenantId", tenantId)
        .routeParam("companyId", companyId)
        .routeParam("invoiceId", invoice.getId())    
        .body(invoiceLine)
        .asObject(InvoiceLineDTO.class)
        .ifSuccess(response -> {
            logger.info(String.format("Invoice Line %s created succesfully", response.getBody().getItemId()));
        })
        .ifFailure(response -> {
            throw new MicrosoftBusinessCentralBadRequestException(response.getBody().toString());
        })
        .getBody();  
    }

}
