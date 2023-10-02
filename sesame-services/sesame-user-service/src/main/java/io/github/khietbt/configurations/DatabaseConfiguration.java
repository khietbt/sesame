package io.github.khietbt.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.khietbt.converters.UuidReadingConverter;
import io.github.khietbt.converters.UuidWritingConverter;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.DialectResolver;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DatabaseConfiguration {
  @Bean
  public R2dbcCustomConversions r2dbcCustomConversions(
    ConnectionFactory connectionFactory
  ) {
    var dialect = DialectResolver.getDialect(connectionFactory);

    var converters = Arrays.asList(
      new UuidReadingConverter(),
      new UuidWritingConverter()
    );

    return R2dbcCustomConversions.of(dialect, converters);
  }

  @Bean
  public ReactiveAuditorAware<String> auditorAware() {
    return () -> Mono.just("admin");
  }
}
