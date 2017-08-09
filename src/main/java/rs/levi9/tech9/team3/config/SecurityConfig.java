package rs.levi9.tech9.team3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration

class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        // ignore the static files
        .antMatchers("/", "/bower_components/**", "/css/**", "/js/**", "/views/**", "/images/**").permitAll()
        .antMatchers(HttpMethod.POST, "/users").permitAll()
        .antMatchers(HttpMethod.POST, "/users/captcha").permitAll()
        // authenticate all remaining URLS
        .anyRequest().fullyAuthenticated().and()
        // enabling the basic authentication
        .httpBasic().and()
        // configuring the session as state less. Which means there is
        // no session in the server
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        // disabling the CSRF - Cross Site Request Forgery
        .csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
