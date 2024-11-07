package com.pageupcomputers.apolloMicroservice.DTO;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItemDTO {
    private String Id;
    private String gtin;
    private String displayName;
    private String type;
    private BigDecimal unitPrice;

    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getGtin() {
        return this.gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ItemDTO() {
    }

    public ItemDTO(OrderItemDTO orderItem) {
        this.gtin = orderItem.getProduct().getEan();
        this.displayName = orderItem.getProduct().getTitle();
        this.type = "Inventory";
        this.unitPrice = orderItem.getUnitPrice();
    }

    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", gtin='" + getGtin() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", type='" + getType() + "'" +
            ", unitPrice='" + getUnitPrice() + "'" +
            "}";
    }
}
