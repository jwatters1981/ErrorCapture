/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.watterssoft;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author johnwatters Apr 27, 2014 11:48:45 AM
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);//jdbcAuthentication().usersByUsernameQuery("select user_name, user_password, enabled from USERS where user_name = ?").passwordEncoder(passwordEncoder).dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		http.authorizeRequests().antMatchers("/resources/**", "/signup", "/about","/jquery/**","/js/**","/logout").permitAll().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/db/**")
				.access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')").anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
		http.csrf().disable();
	}

}
