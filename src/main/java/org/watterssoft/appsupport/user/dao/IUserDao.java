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

package org.watterssoft.appsupport.user.dao;

import org.springframework.data.repository.Repository;
import org.watterssoft.appsupport.user.domain.User;

/**
 * @author johnwatters
 * Apr 28, 2014 2:34:33 PM
 */

public interface IUserDao extends Repository<User, String>{

	public User findByUsername(String username);
	
	public User save(User user);
}
