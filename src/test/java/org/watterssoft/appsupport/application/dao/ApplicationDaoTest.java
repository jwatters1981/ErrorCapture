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

package org.watterssoft.appsupport.application.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.watterssoft.TicketApplication;
import org.watterssoft.appsupport.application.domain.Application;

/**
 * @author johnwatters 15 May 2014 08:21:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketApplication.class)
@WebAppConfiguration
@ActiveProfiles("scratch")
@TransactionConfiguration(defaultRollback = true)
public class ApplicationDaoTest
{

	@Autowired
	private ApplicationDao applicationDao;

	@Test
	public void findApplicationById()
	{
		Application application = new Application("Test App", "1.0", "http://testapp.com/");
		application = applicationDao.save(application);
		assertNotNull(applicationDao.findApplicationById(application.getId()));
		assertEquals("Should have been Test App", "Test App", application.getName());
	}

}
