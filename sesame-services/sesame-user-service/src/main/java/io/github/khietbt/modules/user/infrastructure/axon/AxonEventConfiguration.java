package io.github.khietbt.modules.user.infrastructure.axon;

import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonEventConfiguration {
    @Autowired
    public void queryBus(
            EventBus eventBus,
            AxonMessageDispatchInterceptor axonMessageDispatchInterceptor
    ) {
        eventBus.registerDispatchInterceptor(axonMessageDispatchInterceptor);
    }
}
