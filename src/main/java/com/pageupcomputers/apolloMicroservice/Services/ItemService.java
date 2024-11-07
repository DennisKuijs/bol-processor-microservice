package com.pageupcomputers.apolloMicroservice.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.pageupcomputers.apolloMicroservice.DTO.ItemDTO;
import com.pageupcomputers.apolloMicroservice.DTO.ItemsDTO;
import com.pageupcomputers.apolloMicroservice.DTO.OrderItemDTO;
import com.pageupcomputers.apolloMicroservice.Exceptions.MicrosoftBusinessCentralBadRequestException;
import kong.unirest.Unirest;

/**
 * Service which handles all the requests to the Microsoft Business Central API regaring items (products)
 */
@Service
public class ItemService {

    @Value("${microsoft.businesscentral.tenantId}")
    private String tenantId;

    @Value("${microsoft.businesscentral.company.Id}")
    private String companyId;

    @Value("${api.url.getItems}")
    private String getItemsURL;

    @Value("${api.url.createItem}")
    private String createItemURL;

    @Autowired
    private MicrosoftAuthenticateService microsoftAuthenticateService;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    /**
     * API Call to the Microsoft Business Central API to fetch the items (products) based on the given GTIN (EAN-13 Code)
     * @param orderItem
     * @return ItemsDTO with a list of found items
     */
    public ItemsDTO getItems(OrderItemDTO orderItem) {
        
        logger.debug(String.format("orderItem Contents: %s", orderItem.toString()));

        microsoftAuthenticateService.checkAccessToken();

        return Unirest.get(getItemsURL)
        .header("Authorization", "Bearer " + System.getProperty("accessToken"))
        .routeParam("tenantId", tenantId)
        .routeParam("companyId", companyId)
        .routeParam("gtin", orderItem.getProduct().getEan())
        .asObject(ItemsDTO.class)
        .ifSuccess(response -> {
            logger.info(String.format("There are %s items found", response.getBody().getItems().size()));
        })
        .ifFailure(response -> {
            throw new MicrosoftBusinessCentralBadRequestException(response.getBody().toString());
        })
        .getBody();
    }

    /**
     * API call to the Microsoft Business Central API to create a new iten based on the order item details
     * @param orderItem
     * @return ItemDTO with the item (product) details received from Microsoft Business Central
     */
    public ItemDTO createItem(OrderItemDTO orderItem) {

        logger.debug(String.format("orderItem Contents: %s", orderItem.toString()));

        microsoftAuthenticateService.checkAccessToken();

        ItemDTO itemDTO = new ItemDTO(orderItem);

        return Unirest.post(createItemURL)
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + System.getProperty("accessToken"))
        .routeParam("tenantId", tenantId)
        .routeParam("companyId", companyId)
        .body(itemDTO)
        .asObject(ItemDTO.class)
        .ifSuccess(response -> {
            logger.info(String.format("Item %s created succesfully", response.getBody().getGtin()));
        })
        .ifFailure(response -> {
            throw new MicrosoftBusinessCentralBadRequestException(response.getBody().toString());
        })
        .getBody();
    }

}
