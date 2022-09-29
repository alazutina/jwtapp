package com.anna.jwtappdemo.config;

import com.anna.jwtappdemo.security.jwt.JwtConfigurer;
import com.anna.jwtappdemo.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT="/api/v1/admin/**";
    private static final String MODERATOR_ENDPOINT="/api/v1/moderator/**";
    private static final String LOGIN_ENDPOINT ="/api/v1/auth/login";
    private static final String FILES_ENDPOINT ="/api/v1/files";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider){

        this.jwtTokenProvider=jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(FILES_ENDPOINT).hasAnyAuthority()
                .antMatchers(MODERATOR_ENDPOINT).hasRole("MODERATOR")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}