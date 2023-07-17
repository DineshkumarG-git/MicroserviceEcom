package org.dinesh.config;

import jakarta.servlet.http.HttpServletRequest;
import org.dinesh.filters.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpRequest;

@Configuration
@EnableWebFluxSecurity

public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
//        var temp = (HttpServletRequest)request;
//        temp.getCookies();
//       return   serverHttpSecurity.csrf().disable().authorizeExchange((exchange) -> {
//            exchange.anyExchange().
//            ((ServerHttpSecurity.AuthorizeExchangeSpec.Access) exchange.pathMatchers(new String[]{"/**"})).permitAll().anyExchange().authenticated();
//
//        }).build();
       return  serverHttpSecurity.csrf().disable().authorizeExchange().and().build();





        //serverHttpSecurity.addFilterBefore((WebFilter) new AuthFilter(),SecurityWebFiltersOrder.AUTHENTICATION);


     //   return serverHttpSecurity.build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new AuthFilter(), BasicAuthenticationFilter.class);
//        return http.build();
//    }

}
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("**/**").permitAll()
//                .anyRequest().authenticated();
//                return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }




