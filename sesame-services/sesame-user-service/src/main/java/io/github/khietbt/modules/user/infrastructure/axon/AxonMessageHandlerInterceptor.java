package io.github.khietbt.modules.user.infrastructure.axon;

import io.github.khietbt.shared.domain.exceptions.DomainException;
import io.github.khietbt.shared.domain.exceptions.ExceptionDetails;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.lang.invoke.MethodHandles;

@Component
public class AxonMessageHandlerInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Object handle(@Nonnull UnitOfWork<? extends CommandMessage<?>> unitOfWork,
                         @Nonnull InterceptorChain interceptorChain) throws Exception {
        try {
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
