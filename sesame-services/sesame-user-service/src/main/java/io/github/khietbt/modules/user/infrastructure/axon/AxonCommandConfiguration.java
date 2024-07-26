package io.github.khietbt.modules.user.infrastructure.axon;

import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonCommandConfiguration {

    @Autowired
    void commandBus(
            CommandBus commandBus,
            AxonMessageHandlerInterceptor exceptionWrappingHandlerInterceptor
    ) {
        //noinspection resource
        commandBus.registerHandlerInterceptor(exceptionWrappingHandlerInterceptor);
    }
}
