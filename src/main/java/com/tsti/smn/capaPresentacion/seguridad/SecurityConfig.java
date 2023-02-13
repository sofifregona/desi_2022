package com.tsti.smn.capaPresentacion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tsti.smn.capaServicios.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
    	return NoOpPasswordEncoder.getInstance();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    	auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
   	
    	http.authorizeRequests()                                                          
//            .antMatchers("/resources/**", "/signup", "/about").permitAll()            
//          .antMatchers("/personasEditar").hasAuthority("EDIT")                                
//          .antMatchers("/db/**/**").access("hasRole('ADMIN') and hasRole('DBA')") 
//        	.antMatchers("/ciudadEditarClima").hasAuthority("ADMIN")      
            .anyRequest().authenticated()                                         
            .and()
        .formLogin();
    }
}
