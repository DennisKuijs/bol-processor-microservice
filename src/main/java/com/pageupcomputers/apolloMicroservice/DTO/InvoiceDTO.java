package com.pageupcomputers.apolloMicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pageupcomputers.apolloMicroservice.Utils.LocalDateTimeHelper;
import com.pageupcomputers.apolloMicroservice.Utils.ShipmentDetailsNameHelper;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InvoiceDTO {
    private String Id;
    private String number;
    private String invoiceDate;
    private String postingDate;
    private String customerId;
    private String customerPurchaseOrderReference;
    private String shipToName;
    private String shipToContact;
    private String shipToAddressLine1;
    private String shipToAddressLine2;
    private String shipToPostCode;
    private String shipToCity;
    private String shipToCountry;

    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    
    public String getPostingDate() {
        return this.postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    } 

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPurchaseOrderReference() {
        return this.customerPurchaseOrderReference;
    }

    public void setCustomerPurchaseOrderReference(String customerPurchaseOrderReference) {
        this.customerPurchaseOrderReference = customerPurchaseOrderReference;
    }

    public String getShipToName() {
        return this.shipToName;
    }

    public void setShipToName(String shipToName) {
        this.shipToName = shipToName;
    }

    public String getShipToContact() {
        return this.shipToContact;
    }

    public void setShipToContact(String shipToContact) {
        this.shipToContact = shipToContact;
    }

    public String getShipToAddressLine1() {
        return this.shipToAddressLine1;
    }

    public void setShipToAddressLine1(String shipToAddressLine1) {
        this.shipToAddressLine1 = shipToAddressLine1;
    }

    public String getShipToAddressLine2() {
        return this.shipToAddressLine2;
    }

    public void setShipToAddressLine2(String shipToAddressLine2) {
        this.shipToAddressLine2 = shipToAddressLine2;
    }

    public String getShipToPostCode() {
        return this.shipToPostCode;
    }

    public void setShipToPostCode(String shipToPostCode) {
        this.shipToPostCode = shipToPostCode;
    }

    public String getShipToCity() {
        return this.shipToCity;
    }

    public void setShipToCity(String shipToCity) {
        this.shipToCity = shipToCity;
    }

    public String getShipToCountry() {
        return this.shipToCountry;
    }

    public void setShipToCountry(String shipToCountry) {
        this.shipToCountry = shipToCountry;
    }

    public InvoiceDTO() {
    }

    public InvoiceDTO(OrderDTO order, CustomerDTO customer) {
        this.postingDate = LocalDateTimeHelper.convertOffsetDateTimeToLocalDate(order.getOrderPlacedDateTime());
        this.invoiceDate = LocalDateTimeHelper.convertOffsetDateTimeToLocalDate(order.getOrderPlacedDateTime());
        this.customerPurchaseOrderReference = order.getOrderId();
        this.customerId = customer.getId();
        this.shipToName = ShipmentDetailsNameHelper.chooseShipmentDetailsName(order.getShipmentDetails(), order.getPickUpPoint());
        this.shipToContact = order.getShipmentDetails().getCompany() != null || order.getPickUpPoint() ? order.getShipmentDetails().getFullName() : null;
        this.shipToAddressLine1 = order.getShipmentDetails().getFullStreetName();
        this.shipToAddressLine2 = order.getShipmentDetails().getExtraAddressInformation();
        this.shipToCity = order.getShipmentDetails().getCity();
        this.shipToCountry = order.getShipmentDetails().getCountryCode();
        this.shipToPostCode = order.getShipmentDetails().getZipCode();
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", number='" + getNumber() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", postingDate='" + getPostingDate() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerPurchaseOrderReference='" + getCustomerPurchaseOrderReference() + "'" +
            ", shipToName='" + getShipToName() + "'" +
            ", shipToContact='" + getShipToContact() + "'" +
            ", shipToAddressLine1='" + getShipToAddressLine1() + "'" +
            ", shipToAddressLine2='" + getShipToAddressLine2() + "'" +
            ", shipToPostCode='" + getShipToPostCode() + "'" +
            ", shipToCity='" + getShipToCity() + "'" +
            ", shipToCountry='" + getShipToCountry() + "'" +
            "}";
    }
}
