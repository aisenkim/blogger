package com.example.blogger.security;

import com.example.blogger.jwt.JwtConfig;
import com.example.blogger.jwt.JwtTokenVerifier;
import com.example.blogger.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.example.blogger.services.user.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
   private final PasswordEncoder passwordEncoder;
   private final UserServiceImplementation userService;
   private final SecretKey secretKey;
   private final JwtConfig jwtConfig;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
              .csrf().disable()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
              .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
              .authorizeRequests()
              .antMatchers("/", "index", "/css/*", "/js/*", "/login").permitAll()
//                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers("/api/**").permitAll() // comment this line after testing (this takes away the authentication feature)
              .anyRequest()
              .authenticated();

   }

   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setPasswordEncoder(passwordEncoder); // lets password be decoded
      provider.setUserDetailsService(userService);
      return provider;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(daoAuthenticationProvider());
   }
}
