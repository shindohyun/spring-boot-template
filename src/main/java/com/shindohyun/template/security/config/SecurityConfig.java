package com.shindohyun.template.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    log.info("security config...");

    // URI 패턴으로 접근 제한 설정
    http.authorizeRequests()
    .antMatchers("/securityTest/boardList")
    .permitAll();

    http.authorizeRequests()
    .antMatchers("/securityTest/boardRegister")
    .hasRole("MEMBER");

    http.authorizeRequests()
    .antMatchers("/securityTest/noticeList")
    .permitAll();

    http.authorizeRequests()
    .antMatchers("/securityTest/noticeRegister")
    .hasRole("ADMIN");

    http.formLogin(); // 폼 기반 인증

    // 접근 거부 처리자의 URI 지정
    http.exceptionHandling()
    .accessDeniedPage("/securityTest/accessError");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // (테스트) 로그인 가능한 아이디와 패스워드 설정
    auth.inMemoryAuthentication()
    .withUser("member")
    .password("{noop}1234")
    .roles("MEMBER");

    auth.inMemoryAuthentication()
    .withUser("admin")
    .password("{noop}1234")
    .roles("ADMIN");
  }
}
