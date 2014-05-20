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

package org.watterssoft.appsupport.landing.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.watterssoft.appsupport.application.domain.Application;
import org.watterssoft.appsupport.application.service.ApplicationService;
import org.watterssoft.appsupport.landing.domain.LandingDTO;
import org.watterssoft.appsupport.ticket.domain.ListTicketDTO;
import org.watterssoft.appsupport.ticket.domain.Priority;
import org.watterssoft.appsupport.ticket.domain.Ticket;
import org.watterssoft.appsupport.ticket.domain.TicketState;
import org.watterssoft.appsupport.ticket.service.TicketService;
import org.watterssoft.appsupport.user.domain.User;
import org.watterssoft.appsupport.user.domain.UserApplication;

/**
 * @author johnwatters 18 May 2014 07:56:45
 */

public class LandingServiceTest
{

	@Test
	public void testGetLandingPageForUser()
	{
		TicketService ticketService = Mockito.mock(TicketService.class);
		ApplicationService applicationService = Mockito.mock(ApplicationService.class);
		LandingService landingService = new LandingService(ticketService, applicationService);
		List<UserApplication> userApplications = createUserApplicationList();

		Mockito.when(applicationService.getAllUserApplications("user1")).thenReturn(userApplications);
		Mockito.when(ticketService.getNumberOfTicketsforApplicationByState(2l, TicketState.NEW)).thenReturn(150l);
		Mockito.when(ticketService.getNumberOfTicketsforApplicationByState(2l, TicketState.INPROGRESS)).thenReturn(23l);
		Mockito.when(ticketService.getNumberOfTicketsforApplicationByState(2l, TicketState.CLOSED)).thenReturn(3l);
		Date createDateMinusDays = createDateMinusDays(-10);
		List<ListTicketDTO> ticketList = new ArrayList<ListTicketDTO>();
		Ticket ticket = createTicket(TicketState.NEW);
		Ticket ticketNew1 = createTicket(TicketState.NEW);
		Ticket ticketClosed1 = createTicket(TicketState.CLOSED);
		ticketList.add(new ListTicketDTO(ticket));
		ticketList.add(new ListTicketDTO(ticketNew1));
		ticketList.add(new ListTicketDTO(ticketClosed1));
		Mockito.when(ticketService.getTicketsCreatedAfterDate(createDateMinusDays)).thenReturn(ticketList);
		LandingDTO landingDTO = landingService.getLandingPageForUser("user1", createDateMinusDays);
		assertEquals("Should be 150 new tickets", 150l, landingDTO.getOpenTickets());
		assertEquals("Should be 23 inprogress tickets", 23l, landingDTO.getInprogressTickets());
		assertEquals("Should be 3 inprogress tickets", 3l, landingDTO.getClosedTickets());
		assertEquals("Should be 2 new in the list", 2, landingDTO.getOpenTicketsList().size());
		assertEquals("Should be one new in the list", 1, landingDTO.getClosedTicketsList().size());

	}

	/**
	 * @return
	 */
	private Ticket createTicket(TicketState state)
	{
		Ticket ticket = new Ticket("ticket1", new Date(), Priority.P1, state, createApplication());
		return ticket;
	}

	/**
	 * @return
	 */
	private Date createDateMinusDays(int days)
	{
		Calendar instance = Calendar.getInstance();
		instance.set(Calendar.DAY_OF_YEAR, days);
		Date tenDaysPrevious = instance.getTime();
		return tenDaysPrevious;
	}

	/**
	 * @return
	 */
	private List<UserApplication> createUserApplicationList()
	{
		List<UserApplication> userApplications = new ArrayList<UserApplication>();
		Application application = createApplication();

		User user = new User("user1", "");
		userApplications.add(new UserApplication(user, application));
		return userApplications;
	}

	/**
	 * @return
	 */
	private Application createApplication()
	{
		Application application = new Application("App1", "1.0", "http://localhost");
		application.setId(2l);
		return application;
	}
}
