package com.pageupcomputers.apolloMicroservice.DTO;

import java.util.List;

/**
 * DTO for a single order (Order Id for the order that is fetched based on the query parameters)
 */
public class OrderDTO {
    private String orderId;
    private boolean pickupPoint;
    private String orderPlacedDateTime;
    private ShipmentDetailsDTO shipmentDetails;
    private BillingDetailsDTO billingDetails;
    private List<OrderItemDTO> orderItem;

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean getPickUpPoint() {
        return this.pickupPoint;
    }

    public void setPickUpPoint(boolean pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getOrderPlacedDateTime() {
        return this.orderPlacedDateTime;
    }

    public void setOrderPlacedDateTime(String orderPlacedDateTime) {
        this.orderPlacedDateTime = orderPlacedDateTime;
    }

    public ShipmentDetailsDTO getShipmentDetails() {
        return this.shipmentDetails;
    }

    public void setShipmentDetails(ShipmentDetailsDTO shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }

    public BillingDetailsDTO getBillingDetails() {
        return this.billingDetails;
    }

    public void setBillingDetails(BillingDetailsDTO billingDetails) {
        this.billingDetails = billingDetails;
    }

    public List<OrderItemDTO> getOrderItems() {
        return this.orderItem;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItem = orderItems;
    }

    public OrderDTO() {}

    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            ", orderPlacedDateTime='" + getOrderPlacedDateTime() + "'" +
            ", shipmentDetails='" + getShipmentDetails() + "'" +
            ", billingDetails='" + getBillingDetails() + "'" +
            ", orderItems='" + getOrderItems() + "'" +
            "}";
    }
}
