package ro.sek1.RESTful.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig (
    var userDetailsService: UserDetailsService,
    var jwtFilter: JwtFilter,
){
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
    @Bean
    fun authenticationManager(conf: AuthenticationConfiguration): AuthenticationManager{
        return conf.authenticationManager
    }
    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.formLogin{ it.disable() }
            .csrf { it.disable() }
            .cors { it.disable() }
            .httpBasic { it.disable() }

        http.authorizeHttpRequests {
                it.requestMatchers("/health/**","/auth/**")
                    .permitAll()
                it.requestMatchers("/admin/**")
                    .hasAuthority("ADMIN")
                it.requestMatchers("/**")
                    .hasAnyAuthority("USER","ADMIN")
                it.anyRequest()
                    .permitAll()
            }
            .userDetailsService(userDetailsService)
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}