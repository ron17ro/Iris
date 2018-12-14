package com.helpdesk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.helpdesk.service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled =true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
//	@Autowired
//	private AccessDeniedHandler accessDeniedHandler;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/img/**", "/webjars/**","/home","/login","/forgot**",
				 "/reset**","/helpdeskpage","/","/search-dictionary").permitAll()
		.anyRequest().authenticated()
        .and()
        .formLogin()
        	.loginPage("/login")
        .successHandler(successHandler())
		.defaultSuccessUrl("/home")
	    .usernameParameter("username")
	    .passwordParameter("password")
		.permitAll()
	    .and()
	     .httpBasic().disable()
	    .exceptionHandling().accessDeniedPage("/error"); 
	}
	@Bean 
	public BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
	    handler.setUseReferer(true);
	    return handler;
	}
	/*//Create User - admin/admin
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin")
                .roles("USER", "ADMIN");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/", "/*ticket/**").access("hasRole('USER')").and()
                .formLogin();
    }*/
}
