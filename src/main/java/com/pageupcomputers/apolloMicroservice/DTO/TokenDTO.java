package com.pageupcomputers.apolloMicroservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for the Microsoft access token
 */
public class TokenDTO {
    
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("ext_expires_in")
    private String extExpiresIn;

    @JsonProperty("access_token")
    private String accessToken;


    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getExtExpiresIn() {
        return this.extExpiresIn;
    }

    public void setExtExpiresIn(String extExpiresIn) {
        this.extExpiresIn = extExpiresIn;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "{" +
            " tokenType='" + getTokenType() + "'" +
            ", expiresIn='" + getExpiresIn() + "'" +
            ", extExpiresIn='" + getExtExpiresIn() + "'" +
            ", accessToken='" + getAccessToken() + "'" +
            "}";
    }


    public TokenDTO() {}

}
