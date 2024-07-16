package io.github.khietbt.configurations;

import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleMessageConverterConfiguration {
    @Value("${SIMPLE_MESSAGE_CONVERTER_ALLOWED_LIST_PATTERNS}")
    private String[] allowedListPatterns;

    @Bean
    public SimpleMessageConverter converter() {
        var converter = new SimpleMessageConverter();

        converter.addAllowedListPatterns(
                allowedListPatterns
        );

        return converter;
    }
}
