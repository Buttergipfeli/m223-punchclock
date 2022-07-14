package ch.zli.m223.punchclock.config;

import ch.zli.m223.punchclock.encoder.CustomPasswordEncoder;
import ch.zli.m223.punchclock.jwt.JWTAuthorizationFilter;
import ch.zli.m223.punchclock.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/users").hasAnyRole("USER", "MODERATOR")
                .antMatchers(HttpMethod.GET, "/users").hasAnyRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasAnyRole("MODERATOR")
                .antMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole("MODERATOR", "USER")
                .antMatchers(HttpMethod.PUT, "/users/{id}").hasAnyRole("MODERATOR", "USER")
                .antMatchers("/categories").hasAnyRole("USER", "MODERATOR")
                .antMatchers("/mottopurchases").hasAnyRole("USER", "MODERATOR")
                .antMatchers("/motto").hasAnyRole("USER", "MODERATOR")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login").and()
                .httpBasic().and().csrf().disable();

        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtTokenProvider));

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("*");
            }
        };
    }

}
