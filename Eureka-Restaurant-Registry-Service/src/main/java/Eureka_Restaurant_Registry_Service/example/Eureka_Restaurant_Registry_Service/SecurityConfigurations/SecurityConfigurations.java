package Eureka_Restaurant_Registry_Service.example.Eureka_Restaurant_Registry_Service.SecurityConfigurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
String username = System.getenv("EUREKA_USERNAME") != null ? System.getenv("EUREKA_USERNAME") : "admin";
String password = System.getenv("EUREKA_PASSWORD") != null ? System.getenv("EUREKA_PASSWORD") : "eureka_secure_password_123";

UserDetails user = User.builder()
.username(username)
.password(passwordEncoder.encode(password))
.roles("USER", "ADMIN")
.build();

return new InMemoryUserDetailsManager(user);
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
http.csrf(csrf -> csrf.disable())
.authorizeHttpRequests(authz -> authz
.requestMatchers("/actuator/health").permitAll()
.requestMatchers("/eureka/**").authenticated()
.anyRequest().authenticated())
.httpBasic(basic -> {});
return http.build();
}
}
