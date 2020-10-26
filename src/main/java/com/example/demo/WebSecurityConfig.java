package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests()
			//.antMatchers("/", "/log").permitAll()
			//.anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/","/log","/test","/template/**","/group/**", "/edittic/**", "/edittic", "/username",
				"/cticket","/cticket/**","/ticket/**","/dticket/**", "/comment","/deletetic/**", "/cgroup", "/cgroup/**", "/deletegroup/**","/createuser",
				"/h2-console","/h2-console/**","/img/**")
								.permitAll().anyRequest().authenticated()
								.and()
								.formLogin()
				                .loginPage("/login")
				                .permitAll()
				                .and()
				                .logout()
				                .logoutSuccessUrl("/")
				                .permitAll();
		
		//TO ENABLE USE OF H2
        http.csrf().disable();
        http.headers().frameOptions().disable();

		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
