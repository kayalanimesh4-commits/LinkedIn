package com.linkedIn.Api_gateway.filters;

import io.jsonwebtoken.JwtException;
import lombok.Data;
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

            final String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (authorizationHeader.isEmpty() || authorizationHeader == null || authorizationHeader.startsWith("Bearer")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("Authorization token header not found");
                return exchange.getResponse().setComplete();
            }

            final String token = authorizationHeader.split("Bearer ")[1];
            try {
                String userIdFromToken = jwtService.getUserIdFromToken(token);
                ServerWebExchange build = exchange.mutate().request(r -> r.header("X-user_Id", userIdFromToken)).build();
                return chain.filter(build);
            }catch (JwtException ex){
                log.error("JWT Exception: {}"+ ex.getLocalizedMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
        return gatewayFilter;


    };

    @Data
    public static class Config {
        // You can add filter properties here if needed
        // For example, boolean validateSignature; or String secret;
        private boolean isEnabled;
    }
}
