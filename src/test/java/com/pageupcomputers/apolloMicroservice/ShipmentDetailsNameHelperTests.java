package com.pageupcomputers.apolloMicroservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.pageupcomputers.apolloMicroservice.DTO.ShipmentDetailsDTO;
import com.pageupcomputers.apolloMicroservice.Utils.ShipmentDetailsNameHelper;

public class ShipmentDetailsNameHelperTests {
    
    @Test
    public void ShipmentDetailsNameChooser_chooseShipmentDetailsNameWithCompanyAndWithoutPickUpPoint_ReturnsString() {
        
        /**
         * Arrange
         */
        ShipmentDetailsDTO shipmentDetails = new ShipmentDetailsDTO();
        shipmentDetails.setCompany("PageUp Computers V.O.F.");
        shipmentDetails.setFirstName("Dennis");
        shipmentDetails.setSurname("Kuijs");

        boolean pickupPoint = false;

        /**
         * Act
         */
        String shipmentDetailsName = ShipmentDetailsNameHelper.chooseShipmentDetailsName(shipmentDetails, pickupPoint);

        /**
         * Assert
         */
        Assertions.assertThat(shipmentDetailsName).isNotNull();
        Assertions.assertThat(shipmentDetailsName).isEqualTo(shipmentDetails.getCompany());
    }

    @Test
    public void ShipmentDetailsNameChooser_chooseShipmentDetailsNameWithoutCompanyAndWithPickUpPoint_ReturnsString() {
        
        /**
         * Arrange
         */
        ShipmentDetailsDTO shipmentDetails = new ShipmentDetailsDTO();
        shipmentDetails.setCompany(null);
        shipmentDetails.setFirstName("Dennis");
        shipmentDetails.setSurname("Kuijs");

        boolean pickupPoint = true;
        shipmentDetails.setPickupPointName("PLUS Benders: Gelreplein");

        /**
         * Act
         */
        String shipmentDetailsName = ShipmentDetailsNameHelper.chooseShipmentDetailsName(shipmentDetails, pickupPoint);

        /**
         * Assert
         */
        Assertions.assertThat(shipmentDetailsName).isNotNull();
        Assertions.assertThat(shipmentDetailsName).isEqualTo(shipmentDetails.getPickupPointName());
        
    }

    @Test
    public void ShipmentDetailsNameChooser_chooseShipmentDetailsNameWithoutCompanyAndWithoutPickUpPoint_ReturnsString() {
        
        /**
         * Arrange
         */
        ShipmentDetailsDTO shipmentDetails = new ShipmentDetailsDTO();
        shipmentDetails.setCompany(null);
        shipmentDetails.setFirstName("Dennis");
        shipmentDetails.setSurname("Kuijs");

        boolean pickupPoint = false;

        /**
         * Act
         */
        String shipmentDetailsName = ShipmentDetailsNameHelper.chooseShipmentDetailsName(shipmentDetails, pickupPoint);
        
        /**
         * Assert
         */
        Assertions.assertThat(shipmentDetailsName).isNotNull();
        Assertions.assertThat(shipmentDetailsName).isEqualTo(shipmentDetails.getFullName());
    }

    @Test
    public void ShipmentDetailsNameChooser_chooseShipmentDetailsNameWithCompanyAndWithPickUpPoint_ReturnsString() {
        
        /**
         * Arrange
         */
        ShipmentDetailsDTO shipmentDetails = new ShipmentDetailsDTO();
        shipmentDetails.setCompany("PageUp Computers V.O.F.");
        shipmentDetails.setFirstName("Dennis");
        shipmentDetails.setSurname("Kuijs");

        boolean pickupPoint = true;
        shipmentDetails.setPickupPointName("PLUS Benders: Gelreplein");

        /**
         * Act
         */
        String shipmentDetailsName = ShipmentDetailsNameHelper.chooseShipmentDetailsName(shipmentDetails, pickupPoint);

        /**
         * Assert
         */
        Assertions.assertThat(shipmentDetailsName).isNotNull();
        Assertions.assertThat(shipmentDetailsName).isEqualTo(shipmentDetails.getCompany());

    }
}
