package com.uday.api.bookws.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsServiceImpl userDetailsService;
    private BooksWsAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsServiceImpl userDetailsService,
                          BooksWsAuthenticationEntryPoint authenticationEntryPoint) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                //.antMatchers("/v1/books/{bookId}").hasAnyAuthority("USER", "ADMIN")
                //.antMatchers("/v1/books").hasAuthority("ADMIN")
                .antMatchers("/v1/books/{bookId}").access("hasRole('USER') and hasAuthority('GET_BOOK')")
                .antMatchers("/v1/books").access("hasRole('ADMIN') and hasAuthority('CREATE_BOOK')")
                .anyRequest().authenticated()
                //.and().httpBasic()
                //.authenticationEntryPoint(authenticationEntryPoint);
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}