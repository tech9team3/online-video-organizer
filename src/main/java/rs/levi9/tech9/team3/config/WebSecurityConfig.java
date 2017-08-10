package rs.levi9.tech9.team3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	OrganizerUserService organizerUserService;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(organizerUserService);
    }

	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        super.configure(web);
	    }
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	            .antMatchers("/", "/bower_components/**", "/css/**", "/js/**", "/views/**", "/images/**").permitAll()
	            .antMatchers(HttpMethod.POST, "/users").permitAll()
	            .anyRequest().fullyAuthenticated().and()
	            .httpBasic().and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	            .csrf().disable();
	    }
	    
}
