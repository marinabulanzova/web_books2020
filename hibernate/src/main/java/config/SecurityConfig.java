package config;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import security.AuthProvider;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("security")

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/register").anonymous()
                .antMatchers( "/search_books", "/detailed_books").permitAll()
                //.antMatchers("/books").authenticated()
                .antMatchers("/add_book", "/edit_books", "/rm_books", "/edit_done_orders", "/orders",
                        "/search_orders", "/edit_orders", "rm_orders", "/users", "/detailed_users", "/search_users", "rm_users").access("hasAnyAuthority('ADMIN')")
                .antMatchers("/add_basket", "/basket", "my_orders", "/rm_book_basket").access("hasAnyAuthority('USER')")
                .antMatchers("/account", "/edit_account", "/rm_account").access("hasAnyAuthority('USER', 'ADMIN')")
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error=true")
                .usernameParameter("e_mail")
                .passwordParameter("password")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

