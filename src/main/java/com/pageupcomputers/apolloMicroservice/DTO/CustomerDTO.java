package com.pageupcomputers.apolloMicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pageupcomputers.apolloMicroservice.Utils.SalutationHelper;

/**
 * DTO for a single Customer
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDTO {
    private String Id;
    private String salutation;
    private String displayName;
    private String companyName;
    private String cocnumber;
    private String marketplace;
    private String type;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String postalCode;
    private String email;
    private Boolean taxLiable;
    private String taxRegistrationNumber;

    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSalutation() {
        return this.salutation;
    }
    
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCocnumber() {
        return this.cocnumber;
    }

    public void setCocnumber(String cocnumber) {
        this.cocnumber = cocnumber;
    }

    public String getMarketplace() {
        return this.marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isTaxLiable() {
        return this.taxLiable;
    }

    public Boolean getTaxLiable() {
        return this.taxLiable;
    }

    public void setTaxLiable(Boolean taxLiable) {
        this.taxLiable = taxLiable;
    }

    public String getTaxRegistrationNumber() {
        return this.taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber;
    }

    public CustomerDTO() {
    }

    public CustomerDTO(BillingDetailsDTO billingDetails) {
        this.salutation = SalutationHelper.convertSalutation(billingDetails.getSalutation());
        this.displayName = billingDetails.getFullName();
        this.type = billingDetails.getCompany() != null && billingDetails.getVatNumber() != null && billingDetails.getKvkNumber() != null ? "Company" : "Person";
        this.addressLine1 = billingDetails.getFullStreetName();
        this.addressLine2 = billingDetails.getExtraAddressInformation();
        this.city = billingDetails.getCity();
        this.country = billingDetails.getCountryCode();
        this.postalCode = billingDetails.getZipCode();
        this.email = billingDetails.getEmail();
        this.companyName = billingDetails.getCompany();
        this.cocnumber = billingDetails.getKvkNumber();
        this.marketplace = "BOL.COM";
        this.taxRegistrationNumber = billingDetails.getVatNumber();
        this.taxLiable = true;
    }
    
    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", salutation='" + getSalutation() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", cocnumber='" + getCocnumber() + "'" +
            ", marketplace='" + getMarketplace() + "'" +
            ", type='" + getType() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", email='" + getEmail() + "'" +
            ", taxLiable='" + isTaxLiable() + "'" +
            ", taxRegistrationNumber='" + getTaxRegistrationNumber() + "'" +
            "}";
    }       
}
