package com.pageupcomputers.apolloMicroservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.pageupcomputers.apolloMicroservice.Utils.SalutationHelper;

public class SalutationHelperTests {
    
    @Test
    public void SalutationHelper_convertSalutationWithMale_ReturnsString() {
        
        /**
         * Arrange
         */
        String salutation = "MALE";

        /**
         * Act
         */
        String newSalutation = SalutationHelper.convertSalutation(salutation);

        /**
         * Assert
         */

         Assertions.assertThat(newSalutation).isNotNull();
         Assertions.assertThat(newSalutation).isEqualTo("Dhr.");
    }

    @Test
    public void SalutationHelper_convertSalutationWithFemale_ReturnsString() {
        
        /**
         * Arrange
         */
        String salutation = "FEMALE";

        /**
         * Act
         */
        String newSalutation = SalutationHelper.convertSalutation(salutation);

        /**
         * Assert
         */

         Assertions.assertThat(newSalutation).isNotNull();
         Assertions.assertThat(newSalutation).isEqualTo("Mevr.");
    }

    @Test
    public void SalutationHelper_convertSalutationWithUnknown_ReturnsString() {
        
        /**
         * Arrange
         */
        String salutation = "UNKNOWN";

        /**
         * Act
         */
        String newSalutation = SalutationHelper.convertSalutation(salutation);

        /**
         * Assert
         */

         Assertions.assertThat(newSalutation).isEqualTo(null);
    }
}
