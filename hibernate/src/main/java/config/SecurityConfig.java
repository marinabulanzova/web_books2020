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
                //.defaultSuccessUrl("/login")
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

/*import authentication.DBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.security.SecureRandom;

@Order(1)
@Configuration
@EnableWebSecurity
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("authentication")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DBAuthenticationService dbAuthenticationService;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(dbAuthenticationService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/", "/login", "/register", "/books")
                .permitAll();
        httpSecurity.authorizeRequests().antMatchers("/orders")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        httpSecurity.authorizeRequests().antMatchers("/users")
                .access("hasRole('ROLE_ADMIN')");
        httpSecurity.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/login")
                .failureUrl("/login?error=true")
                .usernameParameter("e_mail")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/books");
    }
}*/

