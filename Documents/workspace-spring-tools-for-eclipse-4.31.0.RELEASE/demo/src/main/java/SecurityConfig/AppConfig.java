package SecurityConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import java.awt.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppConfig {

    private final String Authorizade="/Authorizede";

    private final String register="/register";

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        UserDetails userDetails =User.builder()
                .username("ahmet")
                .password(bCryptPasswordEncoder().encode("tek"))
                .roles("User","Admin")
                .build();

        UserDetails admin=User.builder().
                username("vali")
                .password(bCryptPasswordEncoder().encode("345"))
                .roles("Admin","User").build();

        return new InMemoryUserDetailsManager(userDetails, admin);


    }
    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {

        http
                .headers(frame->frame.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
                .csrf(csrf->csrf.disable())
                .formLogin(formLogin->formLogin.disable())
                .authorizeHttpRequests(authorize->authorize.requestMatchers(
                        Authorizade,register
                        ).permitAll().anyRequest().authenticated()


                )

                .httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }



}
