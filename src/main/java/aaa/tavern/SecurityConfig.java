package aaa.tavern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public PasswordEncoder getEncoder() {
                return new BCryptPasswordEncoder(11);
        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry
                                .addMapping("/api/**")
                                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                                .allowedOrigins("http://localhost:4200")
                                .allowCredentials(true);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf().disable();

                /*
                 * Les données de la session sont enregistrées dans un jeton d’authentification
                 * délivré au client. (JWT)
                 */
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.authenticationProvider(authProvider());
                http.authorizeRequests().anyRequest().permitAll();

                return http.build();
        }

        @Bean
        public DaoAuthenticationProvider authProvider() {
                final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(getEncoder());
                return authProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration)
                        throws Exception {
                return authConfiguration.getAuthenticationManager();
        }

}
