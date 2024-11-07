package com.pageupcomputers.apolloMicroservice.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalDateTimeHelper {
    
    /**
     * Instanstiate the Logger class
     */
    public static Logger logger = LoggerFactory.getLogger(LocalDateTimeHelper.class);

    /**
     * Function to convert a Date object into a LocalDateTime object
     * @param dateTime
     * @return LocalDateTime based on the Date object that were sent
     */
    public static LocalDateTime convertDateToLocalDateTime(Date dateTime) {
        logger.debug(String.format("dateTime contents: %s", dateTime));
        return dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Function to convert a OffsetDateTime (as String) into a LocalDate (as String)
     * @param offSetDateTimeString
     * @return LocalDate (as String) based on the OffsetDateTime (as String)
     */
    public static String convertOffsetDateTimeToLocalDate(String offSetDateTimeString) {
        logger.debug(String.format("offSetDateTimeString contents: %s", offSetDateTimeString));
        LocalDate localDate = OffsetDateTime.parse(offSetDateTimeString).atZoneSameInstant(ZoneId.systemDefault()).toLocalDate();
        return localDate.toString();
    }
}
