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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.watterssoft.appsupport.application.service.ApplicationService;
import org.watterssoft.appsupport.landing.domain.LandingDTO;
import org.watterssoft.appsupport.ticket.domain.TicketDTO;
import org.watterssoft.appsupport.ticket.domain.TicketState;
import org.watterssoft.appsupport.ticket.service.TicketService;
import org.watterssoft.appsupport.user.domain.UserApplication;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * @author johnwatters 16 May 2014 22:23:09
 */
@Service
public class LandingService
{

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ApplicationService applicationService;

	public LandingDTO getLandingPageForUser(String username)
	{
		List<UserApplication> userApplications = applicationService.getAllUserApplications(username);
		long openTicketsForApplications = 0, inprogressTicketsForApplication = 0, closedTicketsForApplications = 0;
		for (UserApplication userApplication : userApplications)
		{
			openTicketsForApplications += ticketService.getNumberOfTicketsforApplicationByState(userApplication.getApplication().getId(), TicketState.NEW);
			inprogressTicketsForApplication += ticketService.getNumberOfTicketsforApplicationByState(userApplication.getApplication().getId(), TicketState.INPROGRESS);
			closedTicketsForApplications += ticketService.getNumberOfTicketsforApplicationByState(userApplication.getApplication().getId(), TicketState.CLOSED);
		}
		Date tenDaysPrevious = createDateMinusDays(-10);
		List<TicketDTO> allRecentTickets = ticketService.getTicketsCreatedAfterDate(tenDaysPrevious);
		Collection<TicketDTO> openTicketList = Collections2.filter(allRecentTickets, TICKET_STATE_PREDICATE_OPEN);
		Collection<TicketDTO> inProgressList = Collections2.filter(allRecentTickets, TICKET_STATE_PREDICATE_INPROGRESS);
		return new LandingDTO(openTicketsForApplications, inprogressTicketsForApplication, closedTicketsForApplications, allRecentTickets, new ArrayList<TicketDTO>(openTicketList),
				new ArrayList<TicketDTO>(inProgressList));

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

	private static Predicate<TicketDTO> TICKET_STATE_PREDICATE_OPEN = new Predicate<TicketDTO>()
	{
		public boolean apply(TicketDTO ticket)
		{
			return ticket.getState().equalsIgnoreCase(TicketState.OPEN.getState());
		}
	};

	private static Predicate<TicketDTO> TICKET_STATE_PREDICATE_INPROGRESS = new Predicate<TicketDTO>()
	{
		public boolean apply(TicketDTO ticket)
		{
			return ticket.getState().equalsIgnoreCase(TicketState.INPROGRESS.getState());
		}
	};
}
