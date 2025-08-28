package com.linkedIn.Api_gateway.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.io.ObjectInputFilter;

@Slf4j
@Component

public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private final JwtService jwtService;

    public AuthFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(AuthFilter.Config config) {
        GatewayFilter gatewayFilter = (exchange, chain) -> {
            log.info("Login request: {}", exchange.getRequest().getURI());
            final String tokenHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (tokenHeader.isEmpty() || tokenHeader == null || tokenHeader.startsWith("Bearer")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("Authorization token header not found");
                return exchange.getResponse().setComplete();
            }
            final String token = tokenHeader.split("Bearer")[1];
            String userIdFromToken = jwtService.getUserIdFromToken(token);
            ServerWebExchange build = exchange.mutate().request(r -> r.header("X-user_Id", userIdFromToken)).build();

            return chain.filter(build);
        };
        return gatewayFilter;


    };

    public static class Config {
        // You can add filter properties here if needed
        // For example, boolean validateSignature; or String secret;
    }
}
