package io.github.khietbt.modules.user.infrastructure.axon;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonSerializerConfiguration {
    @Bean
    @Primary
    public Serializer defaultSerializer() {
        var xStream = new XStream();

        xStream.allowTypesByWildcard(
                new String[]{
                        "org.springframework.data.**",
                        "io.github.khietbt.**"
                }
        );

        return XStreamSerializer
                .builder()
                .xStream(xStream)
                .build();
    }
}
