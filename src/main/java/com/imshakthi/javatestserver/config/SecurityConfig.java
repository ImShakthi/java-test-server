package com.imshakthi.javatestserver.config;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import com.imshakthi.javatestserver.filter.JwtAuthFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

  private static final String USER_ROLE = "USER";
  private static final String ADMIN_ROLE = "ADMIN";

  private final PasswordEncoder passwordEncoder;
  private final JwtAuthFilter jwtAuthFilter;
  private final UserDetailsService userDetailsService;

  public SecurityConfig(
      final PasswordEncoder passwordEncoder,
      final JwtAuthFilter jwtAuthFilter,
      final UserDetailsService userDetailsService) {
    this.passwordEncoder = passwordEncoder;
    this.jwtAuthFilter = jwtAuthFilter;
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(
            auth ->
                auth
                    // Public endpoints
                    .requestMatchers("/auth/addNewUser", "/auth/login")
                    .permitAll()

                    // Role-based endpoints
                    .requestMatchers(HttpMethod.GET, "/api/v1/hello")
                    .hasAuthority(USER_ROLE)

                    // All other endpoints require authentication
                    .anyRequest()
                    .authenticated())

        // Stateless session (required for JWT)
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))

        // Set custom authentication provider
        .authenticationProvider(authenticationProvider())

        // Add JWT filter before Spring Security's default filter
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        // Exception handling
        .exceptionHandling(
            ex ->
                ex.authenticationEntryPoint(
                        (request, response, authException) ->
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                    .accessDeniedHandler(
                        (request, response, accessDeniedException) ->
                            response.sendError(HttpServletResponse.SC_NOT_FOUND)));

    return http.build();
  }

  //  // TODO: replace this hardcode with db/config with user and roles
  //  @Bean
  //  public UserDetailsService userDetailsService() {
  //    return new InMemoryUserDetailsManager(
  //        User.withUsername("admin").password("password").roles(ADMIN_ROLE).build(),
  //        User.withUsername("user").password("password").roles(USER_ROLE).build());
  //  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return provider;
  }

  @Bean
  public AuthenticationManager authenticationManager(final AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:3000")); // only allow your frontend
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true); // required if using cookies or Authorization headers

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return source;
  }
}
