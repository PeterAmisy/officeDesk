package net.wevii.officeDesk.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiSecurity (HttpSecurity http) throws Exception {

       return http.authorizeHttpRequests ((auth)-> auth
                .requestMatchers("/api/v1/reservation").hasAnyRole("ADMIN", "USER")
                               .requestMatchers("api/v1/desk").hasRole("ADMIN")
                               .requestMatchers("api/v1/office").hasRole("ADMIN")
                               .anyRequest()
                               .authenticated()
                )
                .build();

    }

    @Bean
    public InMemoryUserDetailsManager userDetailService(){
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
