package com.pageupcomputers.apolloMicroservice.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pageupcomputers.apolloMicroservice.DTO.ShipmentDetailsDTO;

public class ShipmentDetailsNameHelper {
    
    /**
     * Instanstiate the Logger class
     */
    public static Logger logger = LoggerFactory.getLogger(ShipmentDetailsNameHelper.class);

    /**
     * Function to choose the correct ShipmentDetails name based if the companyName or pickupPoint exists.
     * @param shipmentDetails
     * @param pickupPoint
     * @return String with the correct name based on the parameters that were send.
     */
    public static String chooseShipmentDetailsName(ShipmentDetailsDTO shipmentDetails, Boolean pickupPoint) {
        
        logger.debug(String.format("ShipmentDetailsDTO Contents: %s", shipmentDetails.toString()));
        logger.debug(String.format("Pickuppoint Contents: %s", pickupPoint));

        String shipmentName = "";

        if(shipmentDetails.getCompany() != null && !pickupPoint) {
            shipmentName = shipmentDetails.getCompany();
        }
        else if(shipmentDetails.getCompany() == null && pickupPoint) {
            shipmentName = shipmentDetails.getPickupPointName();
        }
        else if(shipmentDetails.getCompany() == null && !pickupPoint) {
            shipmentName = shipmentDetails.getFullName();
        }
        else if(shipmentDetails.getCompany() != null && pickupPoint) {
            shipmentName = shipmentDetails.getCompany();
        }

        return shipmentName;
    }
}
