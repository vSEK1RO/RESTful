package ro.sek1.RESTful.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    fun getEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.formLogin{ it.disable() }
            .csrf { it.disable() }
            .cors { it.disable() }
            .httpBasic { it.disable() }

        http.authorizeHttpRequests {
            it.requestMatchers("/health","/auth")
                .permitAll()
            it.requestMatchers("/admin")
                .hasRole("ADMIN")
            it.requestMatchers("/")
                .hasRole("USER")
            it.anyRequest()
                .authenticated()
        }
        return http.build()
    }
}