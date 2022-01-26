package com.example.springsecurity.config;

import com.example.springsecurity.service.SecurityUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SecurityUserService securityUserService;

    @Autowired
    public void setUserService(SecurityUserService securityUserService) {
        this.securityUserService = securityUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/registration").permitAll()
                .antMatchers("/currency/**").authenticated()
                .and()
//                .httpBasic()
                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/currency.html", true)
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Run passwordEncoder()");
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        log.info("Run daoAuthenticationProvider()");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(securityUserService);
        return authenticationProvider;
    }
}
