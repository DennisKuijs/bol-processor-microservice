package com.pageupcomputers.apolloMicroservice.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalutationHelper {
    
    /**
     * Instanstiate the Logger class
     */
    public static Logger logger = LoggerFactory.getLogger(SalutationHelper.class);

    /**
     * Function to convert a Bol.com salutation into a invoice readable salutation
     * @param salutation
     * @return String with the invoice readable salutation based on the bol.com salutation value
     */
    public static String convertSalutation(String salutation) {
        
        logger.debug(String.format("Salutation Contents: %s", salutation));
        
        String newSalutation = "";
        switch(salutation) {
            case "MALE":
                newSalutation = "Dhr.";
                break;
            case "FEMALE":
                newSalutation = "Mevr.";
                break;
            case "UNKNOWN":
                newSalutation = null;
                break;
        }
        return newSalutation;
    }
}
