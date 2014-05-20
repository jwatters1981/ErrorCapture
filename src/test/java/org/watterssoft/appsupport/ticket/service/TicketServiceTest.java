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

package org.watterssoft.appsupport.ticket.service;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.watterssoft.TicketApplication;
import org.watterssoft.appsupport.application.domain.Application;
import org.watterssoft.appsupport.application.service.ApplicationService;
import org.watterssoft.appsupport.ticket.domain.Priority;
import org.watterssoft.appsupport.ticket.domain.Ticket;
import org.watterssoft.appsupport.ticket.domain.TicketDTO;
import org.watterssoft.appsupport.user.dao.IUserDao;
import org.watterssoft.appsupport.user.domain.User;

/**
 * @author johnwatters
 * 19 May 2014 08:35:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketApplication.class)
@WebAppConfiguration
@ActiveProfiles("scratch")
@TransactionConfiguration( defaultRollback=false)
public class TicketServiceTest
{

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private IUserDao userDao;
	/**
	 * Test method for {@link org.watterssoft.appsupport.ticket.service.TicketService#getTicketById(long)}.
	 */
	@Test
	public void testGetTicketById()
	{
		Application application = createApplication();
		Ticket ticket = createTicket(application);
		Ticket ticketById = ticketService.getTicketById(ticket.getId());
		Assert.assertNotNull(ticketById);
		Assert.assertEquals("Should be desc","desc", ticketById.getDescription());
	}

	/**
	 * @param application
	 * @return
	 */
	private Ticket createTicket(Application application)
	{
		Date date = new Date();
		Ticket ticket = new Ticket("desc", date, Priority.P1, application);
		ticketService.addTicket(ticket);
		return ticket;
	}

	/**
	 * @return
	 */
	private Application createApplication()
	{
		Application application = new Application("App1", "1.0", "http://local");
		User user = new User("user", "");
		userDao.save(user);
		applicationService.save(application, user);
		return application;
	}

	/**
	 * Test method for {@link org.watterssoft.appsupport.ticket.service.TicketService#getAllTicketsForApplicationReadOnly(java.lang.Long, org.springframework.data.domain.PageRequest)}.
	 */
	@Test
	public void testGetAllTicketsForApplicationReadOnly()
	{
		Application createApplication = createApplication();
		createTicket(createApplication);
		List<Ticket> dtos = ticketService.getAllTicketsForApplication(createApplication.getId(), new  PageRequest(0, 10));
		Assert.assertFalse(dtos.isEmpty());
	}

	

}
