package io.github.khietbt.modules.user.infrastructure.axon;

import io.github.khietbt.shared.domain.exceptions.DomainException;
import io.github.khietbt.shared.domain.exceptions.ExceptionDetails;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class AxonMessageHandlerInterceptor implements MessageHandlerInterceptor<Message<?>> {
    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private Converter<Jwt, AbstractAuthenticationToken> authenticationConverter;

    @Override
    public Object handle(
            @Nonnull UnitOfWork<? extends Message<?>> unitOfWork,
            @Nonnull InterceptorChain interceptorChain
    ) throws Exception {
        try {
            var accessToken = (String) unitOfWork.getMessage().getMetaData().get(AxonMetaDataKeys.ACCESS_TOKEN);

            if (accessToken != null) {
                var jwt = jwtDecoder.decode(accessToken);

                var authentication = this.authenticationConverter.convert(jwt);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            return interceptorChain.proceed();
        } catch (Throwable e) {
            if (e instanceof DomainException) {
                throw new CommandExecutionException(
                        "An exception has occurred during command execution", e, new ExceptionDetails(e.getClass().getName(), e.getMessage())
                );
            }

            throw e;
        }
    }
}
