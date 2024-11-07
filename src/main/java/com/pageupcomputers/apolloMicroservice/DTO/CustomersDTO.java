package com.pageupcomputers.apolloMicroservice.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for a list of fetched customers (In case there are multiple customers fetched at the same time)
 */
public class CustomersDTO {

    @JsonProperty("value")
    private List<CustomerDTO> customers;


    public List<CustomerDTO> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }

    public CustomersDTO() {
    }


    @Override
    public String toString() {
        return "{" +
            " customers='" + getCustomers() + "'" +
            "}";
    }
}
