package com.pageupcomputers.apolloMicroservice.Services;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pageupcomputers.apolloMicroservice.DTO.TokenDTO;
import com.pageupcomputers.apolloMicroservice.Exceptions.MicrosoftOAuth2BadCredentialsException;
import com.pageupcomputers.apolloMicroservice.Utils.LocalDateTimeHelper;
import kong.unirest.Unirest;

/**
 * Service which handles all the requests to the Microsoft Business Central API regaring authentication
 */
@Service
public class MicrosoftAuthenticateService {
    
    @Value("${microsoft.businesscentral.tenantId}")
    private String tenantId;

    @Value("${microsoft.businesscentral.clientId}")
    private String clientId;

    @Value("${microsoft.businesscentral.clientSecret}")
    private String clientSecret;

    @Value("${microsoft.businesscentral.scope}")
    private String scope;

    @Value("${api.url.oauth2.token.microsoft}")
    private String oauth2APIURL;

    Logger logger = LoggerFactory.getLogger(MicrosoftAuthenticateService.class);

    /**
     * API Call to the Microsoft OAuth2 endpoint to retrieve a new access token based on the user credentials (TenantId, ClientId and ClientSecret)
     */
    private TokenDTO retrieveToken() {
        return Unirest.post(oauth2APIURL)
        .routeParam("tenantId", tenantId)
        .field("grant_type", "client_credentials")
        .field("client_id", clientId)
        .field("client_secret", clientSecret)
        .field("scope", scope)
        .asObject(TokenDTO.class)
        .ifSuccess(response -> {
            logger.debug(String.format("Created Microsoft OAuth2 Accesstoken, token valid for the next %s seconds", response.getBody().getExpiresIn()));
            System.setProperty("accessToken", response.getBody().getAccessToken());
        })
        .ifFailure(response -> {
            throw new MicrosoftOAuth2BadCredentialsException("There is something wrong with the Microsoft OAuth2 credentials, please make sure the credentials are correct");
        })
        .getBody();
    }

    /**
     * Function to decode the access token (JWT) we receive from the Microsoft OAuth2 Endpoint
     * @param accessToken
     * @return DecodedJWT object
     */
    private DecodedJWT decodeToken(String accessToken) {
        
        logger.debug(String.format("accessToken contents: %s", accessToken));
        
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.decode(accessToken);
        }
        catch (JWTDecodeException e) {
            throw new JWTDecodeException("There was a problem while decoding the Microsoft OAuth2 Accesstoken");
        }

        logger.debug(String.format("Return DecodedJWT %s", decodedJWT));

        return decodedJWT;

    }

    /**
     * Function to check if the access token is still valid based on the expiry time
     * @param accessToken
     * @return Boolean which indicates if the access token is still valid
     */
    private boolean isTokenValid(String accessToken) {
        
        logger.debug(String.format("accessToken contents: %s", accessToken));

        DecodedJWT decodedJWT = decodeToken(accessToken);
        LocalDateTime expiresAt = LocalDateTimeHelper.convertDateToLocalDateTime(decodedJWT.getExpiresAt());

        logger.debug(String.format("Token expires at %s", expiresAt));

        return expiresAt.isAfter(LocalDateTime.now());
    }

    /**
     * Function to check if the access token is still valid and if necessary retrieve a new token
     */
    public void checkAccessToken() {
        String accessToken = System.getProperty("accessToken");
        if(accessToken == null || !isTokenValid(accessToken)) {
            logger.debug("Token doesn't exists or is not valid anymore, creating a new one");
            retrieveToken();
        }
    }
}
