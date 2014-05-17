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

package org.watterssoft.appsupport.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.watterssoft.appsupport.user.dao.IUserRoleMappingDao;
import org.watterssoft.appsupport.user.domain.User;
import org.watterssoft.appsupport.user.domain.UserRoleMapping;

/**
 * @author johnwatters 11 May 2014 09:01:11
 */

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService
{

	@Autowired
	public UserService userService;
	
	@Autowired
	private IUserRoleMappingDao userRoleMappngDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User dbUser = userService.getUserByUserName(username);
		ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		List<UserRoleMapping> userRoleMappings = userRoleMappngDao.findUserRoleMappingByUserName(username);
		for (UserRoleMapping userRoleMapping : userRoleMappings)
		{
			roles.add(new SimpleGrantedAuthority(userRoleMapping.getRole().getName()));
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, dbUser.getPassword(), roles);
		return userDetails;
	}

}
