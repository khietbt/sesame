package io.github.khietbt.configurations;

import org.springframework.cloud.gateway.server.mvc.filter.RemoveHopByHopResponseHeadersFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.removeRequestHeader;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouterFunction<ServerResponse> sesameUserServiceRoute(RemoveHopByHopResponseHeadersFilter removeHopByHopResponseHeadersFilter) {
        return route("sesame-user-service")
                .route(path("/api/v1/sesame-user-service/**"), http())
                .filter(lb("sesame-user-service"))
                .before(
                        rewritePath(
                                "/api/v1/sesame-user-service/(?<remains>.*)", "/${remains}"
                        )
                )
                .before(removeRequestHeader("Origin"))
                .build();
    }
}
