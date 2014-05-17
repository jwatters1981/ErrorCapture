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

package org.watterssoft.appsupport.user.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author johnwatters 11 May 2014 08:11:49
 */
@Controller
@Scope(value = "session")
@RequestMapping(value = "/authenicateduser")
public class AuthenicatedUserController
{

	@Autowired
	private UserDetailsService userService;

	@RequestMapping(value = "/retrieve", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UserRoles authenticatedUser()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails))
		{
			return null;
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		ArrayList<GrantedAuthority> grantedRoles = new ArrayList<GrantedAuthority>(authorities);
		UserRoles userRoles = new UserRoles(userDetails.getUsername(), grantedRoles);
		return userRoles;
	}
}
