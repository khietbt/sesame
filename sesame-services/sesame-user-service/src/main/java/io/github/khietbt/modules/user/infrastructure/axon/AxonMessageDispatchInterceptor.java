package io.github.khietbt.modules.user.infrastructure.axon;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

@Component
@Slf4j
public class AxonMessageDispatchInterceptor implements MessageDispatchInterceptor<Message<?>> {
    @Nonnull
    @Override
    public BiFunction<Integer, Message<?>, Message<?>> handle(@Nonnull List<? extends Message<?>> messages) {
        return (index, message) -> {
            var accessToken = message.getMetaData().get(AxonMetaDataKeys.ACCESS_TOKEN);

            if (Objects.nonNull(accessToken)) {
                return message;
            }

            var authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwt) {
                var metaData = message.getMetaData().and(AxonMetaDataKeys.ACCESS_TOKEN, jwt.getToken().getTokenValue());

                return message.andMetaData(metaData);
            }

            return message;
        };
    }
}
