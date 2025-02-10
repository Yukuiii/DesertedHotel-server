package com.yukuii.desertedhotel.fiter;

import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.yukuii.desertedhotel.common.exception.UnauthorizedException;
import cn.dev33.satoken.exception.NotLoginException;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    // 白名单路径
    private static final List<String> WHITE_LIST = List.of(
            "/auth/login",
            "/user/register",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v3/api-docs/**");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 判断是否白名单路径
        if (isWhitePath(path)) {
            return chain.filter(exchange);
        }

        // 检查是否登录
        try {
            SaRouter.match("/**")
                    .check(r -> StpUtil.checkLogin());
        } catch (NotLoginException e) {
            throw new UnauthorizedException(e.getMessage());
        }

        return chain.filter(exchange);

    }

    // 判断是否是白名单路径
    private boolean isWhitePath(String path) {
        return WHITE_LIST.stream()
                .anyMatch(pattern -> path.startsWith(pattern) || path.matches(pattern.replace("/**", "/.*")));
    }

    // 设置优先级
    @Override
    public int getOrder() {
        return -100;
    }
}