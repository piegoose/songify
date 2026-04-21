package pl.piegoose.songify.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.piegoose.songify.domain.usercrud.UserRepository;

@Configuration
class SecurityConfig {

    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository, passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-resources").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("users/register/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/songs/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/artist/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/album/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/genre/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/songs/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/songs/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH,"/songs/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/songs/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/artist/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/artist/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH,"/artist/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/artist/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/album/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/album/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/genre/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")

                .anyRequest().authenticated());
        return http.build();
    }



}
