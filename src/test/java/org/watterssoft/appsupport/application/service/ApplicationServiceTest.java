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

package org.watterssoft.appsupport.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.watterssoft.appsupport.application.dao.ApplicationDao;
import org.watterssoft.appsupport.application.domain.Application;
import org.watterssoft.appsupport.application.domain.ApplicationDTO;
import org.watterssoft.appsupport.ticket.dao.TicketDao;
import org.watterssoft.appsupport.ticket.domain.TicketState;
import org.watterssoft.appsupport.user.dao.IUserApplicationDao;
import org.watterssoft.appsupport.user.domain.User;
import org.watterssoft.appsupport.user.domain.UserApplication;

/**
 * @author johnwatters 10 May 2014 08:36:36
 */

public class ApplicationServiceTest
{

	/**
	 * Test method for {@link org.watterssoft.appsupport.application.service.ApplicationService#getAllUserApplications(java.lang.String)}.
	 */
	@Test
	public void testGetAllUserApplications()
	{
		ApplicationDao applicationDao = mock(ApplicationDao.class);
		IUserApplicationDao userApplicationDao = mock(IUserApplicationDao.class);
		TicketDao ticketDao = mock(TicketDao.class);
		List<UserApplication> userApplications = new ArrayList<UserApplication>();
		User user = createUser();
		Application application = createAppllication();
		userApplications.add(new UserApplication(user, application));
		when(userApplicationDao.findUserApplicationByUserName("user1")).thenReturn(userApplications);
		
		ApplicationService applicationService = new ApplicationService(applicationDao, userApplicationDao, ticketDao);
		List<UserApplication> returnedUserApplications = applicationService.getAllUserApplications("user1");
		assertEquals("Should only be " + userApplications.size(), userApplications.size(), returnedUserApplications.size());
	}

	/**
	 * @return
	 */
	private Application createAppllication()
	{
		Application application = new Application("App1", "1.0", "http://local");
		return application;
	}

	/**
	 * Test method for {@link org.watterssoft.appsupport.application.service.ApplicationService#save(org.watterssoft.appsupport.application.domain.Application, org.watterssoft.appsupport.user.domain.User)}.
	 */
	@Test
	public void testSave()
	{
		ApplicationDao applicationDao = mock(ApplicationDao.class);
		IUserApplicationDao userApplicationDao = mock(IUserApplicationDao.class);
		TicketDao ticketDao = mock(TicketDao.class);
		
		ApplicationService applicationService = new ApplicationService(applicationDao, userApplicationDao, ticketDao);
		Application application = createAppllication();
		Application application2 = createAppllication();
		application2.setId(2l);
		when(applicationDao.save(application)).thenReturn(application2);

		User user = createUser();
		application = applicationService.save(application, user);
		assertEquals(application.getId(), application2.getId());
	}

	/**
	 * @return
	 */
	private User createUser()
	{
		return new User("user1", "password");
	}

	/**
	 * Test method for {@link org.watterssoft.appsupport.application.service.ApplicationService#getAllUserApplicationDTO(java.lang.String)}.
	 */
	@Test
	public void testGetAllUserApplicationDTO()
	{
		ApplicationDao applicationDao = mock(ApplicationDao.class);
		IUserApplicationDao userApplicationDao = mock(IUserApplicationDao.class);
		TicketDao ticketDao = mock(TicketDao.class);
		List<UserApplication> userApplications = new ArrayList<UserApplication>();
		User user = createUser();
		Application application = createAppllication();
		application.setId(1l);
		userApplications.add(new UserApplication(user, application));
		when(userApplicationDao.findUserApplicationByUserName("user1")).thenReturn(userApplications);
		when(ticketDao.countTicketsForApplication(1l)).thenReturn(5l);
		when(ticketDao.countTicketsForApplicationByState(1l, TicketState.NEW.getState())).thenReturn(5l);
		ApplicationService applicationService = new ApplicationService(applicationDao, userApplicationDao, ticketDao);
		List<ApplicationDTO> applicationDTOs = applicationService.getAllUserApplicationDTO("user1");
		assertFalse("Should not be empty",applicationDTOs.isEmpty());
	}

}
