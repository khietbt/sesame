package io.github.khietbt.modules.user.infrastructure.axon;

import org.axonframework.queryhandling.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonQueryConfiguration {
    @Autowired
    public void queryBus(
            QueryBus queryBus,
            AxonMessageHandlerInterceptor axonMessageHandlerInterceptor,
            AxonMessageDispatchInterceptor axonMessageDispatchInterceptor
    ) {
        queryBus.registerHandlerInterceptor(axonMessageHandlerInterceptor);
        queryBus.registerDispatchInterceptor(axonMessageDispatchInterceptor);
    }
}
