package com.yukuii.desertedhotel.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.stp.StpUtil;

@Configuration
public class SaTokenConfig {
   /**
    * 注册 [Sa-Token全局过滤器]
    */
   @Bean
   public SaReactorFilter getSaReactorFilter() {
       return new SaReactorFilter()
               // 指定 [拦截路由]
               .addInclude("/**")    /* 拦截所有path */
               // 指定 [放行路由]
               .addExclude("/doc.html")
               .addExclude("/webjars/**")
               .addExclude("/swagger-resources/**")
               .addExclude("/v3/api-docs/**")
               .addExclude("/auth/**")
               // 指定[认证函数]: 每次请求执行
               .setAuth(obj -> {
                   SaRouter.match("/**","/auth/**", StpUtil::checkLogin);
               });
               // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数

   }
}
