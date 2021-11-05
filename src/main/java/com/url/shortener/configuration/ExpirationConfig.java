package com.url.shortener.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpirationConfig {

    @Value("${expiry.default}")
    private int defDays;
}
