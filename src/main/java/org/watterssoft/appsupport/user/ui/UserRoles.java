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
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author johnwatters 11 May 2014 09:09:02
 */

public class UserRoles
{

	private String username;
	private List<String> roles = new ArrayList<String>();

	public UserRoles()
	{
		super();
	}

	public UserRoles(String username, List<GrantedAuthority> grantedRoles)
	{
		super();
		this.username = username;
		for (GrantedAuthority grantedAuthority : grantedRoles)
		{
			roles.add(grantedAuthority.getAuthority());
		}

	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public List<String> getGrantedRoles()
	{
		return roles;
	}

	public void setGrantedRoles(List<String> roles)
	{
		this.roles = roles;
	}

}
