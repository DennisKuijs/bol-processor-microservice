package com.pageupcomputers.apolloMicroservice.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for a list of fetched items (In case there are multiple items fetched at the same time)
 */
public class ItemsDTO {

    @JsonProperty("value")
    private List<ItemDTO> items;


    public List<ItemDTO> getItems() {
        return this.items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public ItemsDTO() {
    }

    @Override
    public String toString() {
        return "{" +
            " items='" + getItems() + "'" +
            "}";
    }
}
