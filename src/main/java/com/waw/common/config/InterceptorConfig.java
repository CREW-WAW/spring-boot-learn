package com.waw.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.waw.common.util.HandlerInterceptorUtil;

@Configuration
public class InterceptorConfig extends HandlerInterceptorAdapter {

  @Bean
  public HandlerInterceptorUtil handshakeInterceptor() {
    return new HandlerInterceptorUtil();
  }
}
