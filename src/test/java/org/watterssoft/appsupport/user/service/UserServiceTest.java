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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.watterssoft.TicketApplication;
import org.watterssoft.appsupport.application.dao.ApplicationDao;
import org.watterssoft.appsupport.application.domain.Application;
import org.watterssoft.appsupport.application.service.ApplicationService;
import org.watterssoft.appsupport.user.dao.IUserApplicationDao;
import org.watterssoft.appsupport.user.dao.IUserDao;
import org.watterssoft.appsupport.user.domain.User;
import org.watterssoft.appsupport.user.domain.UserApplication;

/**
 * @author johnwatters
 * 6 May 2014 21:25:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketApplication.class)
@WebAppConfiguration
@ActiveProfiles("scratch")
@TransactionConfiguration( defaultRollback=false)
public class UserServiceTest {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserApplicationDao userApplicationDao;
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Test
	@Transactional
	public void testSaveUserApplication() {
		Application application = new Application("App 1", "1.0", "http");
		applicationDao.save(application);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();

		String password = encoder.encodePassword("password", null);
		System.err.println("Password ***** "+password);
		User user = new User("user",password);
		user = userDao.save(user);
		UserApplication userApplication = new UserApplication(user , application );
		userApplicationDao.save(userApplication);
		Assert.assertFalse(applicationService.getAllUserApplications("user").isEmpty());
		
		
	}

}
