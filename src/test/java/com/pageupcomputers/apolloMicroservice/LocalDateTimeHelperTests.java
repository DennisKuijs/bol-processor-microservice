package com.pageupcomputers.apolloMicroservice;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.pageupcomputers.apolloMicroservice.Utils.LocalDateTimeHelper;

public class LocalDateTimeHelperTests {

    @Test
    public void LocalDateTimeHelper_convertDateToLocalDateTime_ReturnsLocalDateTime() {
        
        /**
         * Arrange
         */
        Date date = new GregorianCalendar(2023, Calendar.OCTOBER, 3).getTime();
        
        /**
         * Act
         */
        LocalDateTime localDateTime = LocalDateTimeHelper.convertDateToLocalDateTime(date);

        /**
         * Assert
         */
        Assertions.assertThat(localDateTime).isNotNull();
        Assertions.assertThat(localDateTime).isEqualTo(LocalDateTime.of(2023, 10, 3, 0, 0, 0));
    }

    @Test
    public void LocalDateTimeHelper_convertOffsetDateTimeStringtoDateString_ReturnsLocalDateTime() {

        /**
         * Arrange
         */
        String offsetDateTime = "2017-02-09T12:39:48+01:00";

        /**
         * Act
         */
        String localDate = LocalDateTimeHelper.convertOffsetDateTimeToLocalDate(offsetDateTime);

        /**
         * Assert
         */
        Assertions.assertThat(localDate).isNotNull();
        Assertions.assertThat(localDate).isEqualTo("2017-02-09");
    }

}
