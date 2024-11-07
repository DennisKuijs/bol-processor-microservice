package com.pageupcomputers.apolloMicroservice.DTO;

import java.math.BigDecimal;

public class InvoiceLineDTO {
    private String itemId;
    private String lineType;
    private BigDecimal unitPrice;
    private Integer quantity;


    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getLineType() {
        return this.lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public InvoiceLineDTO() {
    }

    public InvoiceLineDTO(OrderItemDTO orderItem, ItemDTO item) {
        this.itemId = item.getId();
        this.lineType = "Item";
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getUnitPrice();
    }

    @Override
    public String toString() {
        return "{" +
            " itemId='" + getItemId() + "'" +
            ", lineType='" + getLineType() + "'" +
            ", unitPrice='" + getUnitPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }
}
