package com.pamihnenkov.supplier.dadata;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dadata.client")
@Data
public class DadataClientProperties {

    /**
     * Dadata base url
     */
    private String baseUrl = "https://suggestions.dadata.ru/suggestions/api/4_1/rs";

    /**
     * Dadata API token
     */
    private String token = "d05e1e0a780ba69ffaff835d1c9832d3da897505";

    /**
     * Request timeout. Default - 5 seconds
     */
    private Duration timeout = Duration.of(5, ChronoUnit.SECONDS);
}