package ir.maktab.project.config;

import ir.maktab.project.data.repository.ManagerRepository;
import ir.maktab.project.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/*
    @author Negin Mousavi
*/


@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    private final ManagerRepository managerRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebConfigSecurity(AuthenticationSuccessHandler authenticationSuccessHandler
            , UserRepository userRepository, ManagerRepository managerRepository) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return new SystemUserDetailsService(userRepository, passwordEncoder(), managerRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/register", "/doRegister", "/error").permitAll()
/*                .antMatchers( "/css/**", "/images/**", "/js/**").permitAll()*/
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().csrf().disable();
    }
}
