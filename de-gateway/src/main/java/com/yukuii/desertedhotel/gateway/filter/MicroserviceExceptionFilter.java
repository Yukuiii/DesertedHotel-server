package com.yukuii.desertedhotel.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukuii.desertedhotel.common.exception.BizException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MicroserviceExceptionFilter implements GlobalFilter, Ordered {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponseDecorator responseDecorator = new ServerHttpResponseDecorator(exchange.getResponse()) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                        // 合并所有数据缓冲区
                        StringBuilder responseBody = new StringBuilder();
                        dataBuffers.forEach(buffer -> {
                            byte[] content = new byte[buffer.readableByteCount()];
                            buffer.read(content);
                            responseBody.append(new String(content, StandardCharsets.UTF_8));
                        });
                        
                        // 检查响应状态码，如果是错误状态码则尝试解析响应体
                        if (getStatusCode() != null && getStatusCode().is4xxClientError() || getStatusCode().is5xxServerError()) {
                            try {
                                Map<String, Object> responseMap = objectMapper.readValue(responseBody.toString(), Map.class);
                                if (responseMap.containsKey("code") && responseMap.containsKey("message")) {
                                    int code = (int) responseMap.get("code");
                                    String message = (String) responseMap.get("message");
                                    
                                    // 将微服务的错误响应转换为BizException
                                    throw new BizException(code, message);
                                }
                            } catch (JsonProcessingException e) {
                                // 解析失败，可能不是预期的JSON格式
                            }
                        }
                        
                        // 重新创建数据缓冲区
                        byte[] uppedContent = responseBody.toString().getBytes(StandardCharsets.UTF_8);
                        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(uppedContent);
                        return buffer;
                    }));
                }
                return super.writeWith(body);
            }
        };
        
        return chain.filter(exchange.mutate().response(responseDecorator).build());
    }
    
    @Override
    public int getOrder() {
        return -2; // 在网关异常处理器之前执行
    }
} 