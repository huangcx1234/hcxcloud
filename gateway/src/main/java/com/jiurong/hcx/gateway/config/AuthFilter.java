package com.jiurong.hcx.gateway.config;/**
 * @Author: yuyf
 * @Description:
 * @Date: Created in 10:28 2019-03-29
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * @ClassName CustomGlobalFilter
 * @Description 全局过滤器
 * @Author Administrator
 * @Date 2019-03-29 10:28
 * @Version 1.0
 **/
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    private static final String LOGIN_PREFIX = "/api/v1/login";
    private static final String AUTH_PREFIX = "/api/v1/auth";
    private static final String TOKEN = "Authorization";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(TOKEN);
        String path = exchange.getRequest().getURI().getPath();
        HttpMethod method = exchange.getRequest().getMethod();
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        log.info("token=[{}],path=[{}],method=[{}],params=[{}]", token, path, method, params);
        //用户登录和企业授权接口直接放行
        if (path.startsWith(LOGIN_PREFIX) || path.startsWith(AUTH_PREFIX)) {
            return chain.filter(exchange);
        }
        //获取不到token则直接拦截
        if (token == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().writeWith(Flux.just(setBody(exchange, -1, "token不能为空")));
        }
        //获取不到已登录信息则直接拦截
        String loginInfo = (String) redisTemplate.opsForValue().get("token_" + token);
        if (loginInfo == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().writeWith(Flux.just(setBody(exchange, -1, "登陆超时，请重新登陆")));
        }
        //往请求头上放入登录信息
        Consumer<HttpHeaders> headersConsumer = httpHeaders1 -> {
            httpHeaders1.add("loginInfo", loginInfo);
        };
        //向headers中放文件，记得build
        ServerHttpRequest host = exchange.getRequest().mutate().headers(headersConsumer).build();
        //将现在的request 变成 change对象
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);
    }


    private DataBuffer setBody(ServerWebExchange exchange, int code, String message) {
        String returnMsg = "{ \"code\":" + code + ",\"message\":\"" + message + "\"}";
        byte[] bytes = returnMsg.getBytes(StandardCharsets.UTF_8);
        return exchange.getResponse().bufferFactory().wrap(bytes);
    }


    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
