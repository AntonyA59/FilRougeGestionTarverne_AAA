package aaa.tavern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public PasswordEncoder getEncoder() {
                return new BCryptPasswordEncoder(11);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((authz) -> authz
                                                .antMatchers("/api/**")
                                                .permitAll())
                                .authorizeHttpRequests((authz) -> authz
                                                .antMatchers("/h2-ui/**")
                                                .permitAll())
                                .authorizeHttpRequests((authz) -> authz
                                                .antMatchers("/register")
                                                .permitAll())
                                .authorizeHttpRequests((authz) -> authz
                                                .antMatchers("/login")
                                                .permitAll())
                                .formLogin()
                                // .successForwardUrl("/game")
                                .loginPage("/login");
                // TODO : A RETIRER QUAND ON UTILISERA PLUS H2 DATABASE
                http.csrf().disable();
                http.headers().frameOptions().disable();

                return http.build();
        }

        @Bean
        public DaoAuthenticationProvider authProvider() {
                final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(getEncoder());
                return authProvider;
        }
}
