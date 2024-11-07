package com.pageupcomputers.apolloMicroservice.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.pageupcomputers.apolloMicroservice.DTO.BillingDetailsDTO;
import com.pageupcomputers.apolloMicroservice.DTO.CustomerDTO;
import com.pageupcomputers.apolloMicroservice.DTO.CustomersDTO;
import com.pageupcomputers.apolloMicroservice.Exceptions.MicrosoftBusinessCentralBadRequestException;
import kong.unirest.Unirest;

/**
 * Service which handles all the requests to the Microsoft Business Central API regaring customers
 */
@Service
public class CustomerService {
    
    @Value("${microsoft.businesscentral.tenantId}")
    private String tenantId;

    @Value("${microsoft.businesscentral.company.Id}")
    private String companyId;

    @Value("${api.url.getCustomers}")
    private String getCustomerAPIURL;

    @Value("${api.url.createCustomer}")
    private String createCustomerAPIURL;

    @Value("${api.url.addBusinessDetails}")
    private String addBusinessDetailsAPIURL;
    
    @Autowired
    private MicrosoftAuthenticateService microsoftAuthenticateService;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    /**
     * API Call to the Microsoft Business Central API to check if a customer exists based on different query parameters (displayName, houseNumber and postalCode)
     * @param displayName
     * @param houseNumber
     * @param postalCode
     * @return CustomersDTO with a list of found customers
     */
    public CustomersDTO getCustomers(BillingDetailsDTO billingDetails) {

        logger.debug(String.format("displayName contents: %s", billingDetails.getFullName()));
        logger.debug(String.format("houseNumber contents: %s", billingDetails.getHousenumber()));
        logger.debug(String.format("postalCode contents: %s", billingDetails.getZipCode()));

        microsoftAuthenticateService.checkAccessToken();

        return Unirest.get(getCustomerAPIURL)
        .header("Authorization", "Bearer " + System.getProperty("accessToken"))
        .routeParam("tenantId", tenantId)
        .routeParam("companyId", companyId)
        .routeParam("displayName", billingDetails.getFullName())
        .routeParam("houseNumber", billingDetails.getHousenumber())
        .routeParam("postalCode", billingDetails.getZipCode())
        .asObject(CustomersDTO.class)
        .ifSuccess(response -> {
            logger.info(String.format("There are %s customers found", response.getBody().getCustomers().size()));
        })
        .ifFailure(response -> {
            throw new MicrosoftBusinessCentralBadRequestException(response.getBody().toString());
        })
        .getBody();
        
    }

    /**
     * API call to the Microsoft Business Central API to create a new customer based on the order billing details
     * @param billingDetails
     * @return CustomerDTO with the customer details received from Microsoft Business Central
     */
    public CustomerDTO createCustomer(BillingDetailsDTO billingDetails) {
        
        logger.debug(String.format("BillingDetailsDTO Contents: %s", billingDetails.toString()));

        microsoftAuthenticateService.checkAccessToken();

        CustomerDTO customer = new CustomerDTO(billingDetails);

        logger.debug(String.format("CustomerDTO Contents: %s", customer.toString()));

        return Unirest.post(createCustomerAPIURL)
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + System.getProperty("accessToken"))
        .routeParam("tenantId", tenantId)
        .routeParam("companyId", companyId)
        .body(customer)
        .asObject(CustomerDTO.class)
        .ifSuccess(response -> {
            logger.info(String.format("Customer %s created succesfully", response.getBody().getDisplayName()));
        })
        .ifFailure(response -> {
            throw new MicrosoftBusinessCentralBadRequestException(response.getBody().toString());
        })
        .getBody();
    }
}
