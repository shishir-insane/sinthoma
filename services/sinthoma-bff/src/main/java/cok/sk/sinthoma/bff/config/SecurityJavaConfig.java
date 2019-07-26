/**
 * SecurityJavaConfig.java
 * sinthoma-bff
 * Copyright 2019 Shishir Kumar
 *
 * Licensed under the GNU Lesser General Public License v3.0
 * Permissions of this license are conditioned on making available complete
 * source code of licensed works and modifications under the same license
 * or the GNU GPLv3. Copyright and license notices must be preserved.
 *
 * Contributors provide an express grant of patent rights. However, a larger
 * work using the licensed work through interfaces provided by the licensed
 * work may be distributed under different terms and without source code for
 * the larger work.
 */
package cok.sk.sinthoma.bff.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import cok.sk.sinthoma.bff.user.security.RestAuthenticationEntryPoint;
import cok.sk.sinthoma.bff.user.security.SinthomaAuthSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private SinthomaAuthSuccessHandler sinthomaAuthSuccessHandler;

    @Bean
    public SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
	return new SimpleUrlAuthenticationFailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN").and()
		.withUser("user").password(encoder().encode("userPass")).roles("USER");
    }

    @Bean
    public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) 
//      throws Exception {
//        http.csrf().disable()
//          .authorizeRequests()
//          .antMatchers("/user/login").permitAll()
//          .anyRequest()
//          .authenticated()
//          .and()
//          .httpBasic();
//    }
    
  //  @Override
    protected void configure123(HttpSecurity httpSecurity) throws Exception {
	httpSecurity.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
	httpSecurity.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(restAuthenticationEntryPoint)
		.and()
		.authorizeRequests()
		.antMatchers("/api/foos").authenticated()
		.antMatchers("/api/admin/**").hasRole("ADMIN")
		.and()
		.formLogin().successHandler(sinthomaAuthSuccessHandler)
		.failureHandler(simpleUrlAuthenticationFailureHandler())
		.and()
		.logout();
    }
}