package com.CollageMSM.CollageMSM.Security;


import com.CollageMSM.CollageMSM.Dao.RegistrationRepo;
import com.CollageMSM.CollageMSM.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class MySecurityConfig
{
    @Autowired
    CustomUserDetailService myUserDetailService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/assets/**", "/", "/assestad/**", "/login","/admin/new/register/","/admin/register/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/student/**","/").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
//                    .loginProcessingUrl("/user/login/")
                .defaultSuccessUrl("/default/", true)
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll()
                .and()
                .rememberMe().rememberMeParameter("remember_me").key("mySecreteKey").tokenValiditySeconds(60 * 60 * 60 * 24 * 7);

        return http.build();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(myUserDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}


