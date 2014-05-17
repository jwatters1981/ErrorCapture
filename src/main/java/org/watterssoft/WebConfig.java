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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author johnwatters Apr 27, 2014 12:00:19 PM
 */
@EnableWebMvc
@ComponentScan(basePackages = { "org.watterssoft.appsupport" })
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/css/**").addResourceLocations("/css/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/jquery/**").addResourceLocations("/jquery/")
		.setCachePeriod(31556926);
		registry.addResourceHandler("/primeui/**").addResourceLocations("/primeui/")
		.setCachePeriod(31556926);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
		.setCachePeriod(31556926);
	}
	
	 @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
	 
	    @Bean
	    public InternalResourceViewResolver getInternalResourceViewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        
	        resolver.setPrefix("/WEB-INF/html/");
	        resolver.setSuffix(".html");
	        return resolver;
	    }
}
