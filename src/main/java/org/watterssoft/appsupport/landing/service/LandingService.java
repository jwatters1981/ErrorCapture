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
import org.watterssoft.appsupport.application.domain.Application;
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

	private TicketService ticketService;

	private ApplicationService applicationService;

	@Autowired
	public LandingService(TicketService ticketService, ApplicationService applicationService)
	{
		super();
		this.ticketService = ticketService;
		this.applicationService = applicationService;
	}

	public LandingDTO getLandingPageForUser(String username, Date fromDate)
	{
		List<UserApplication> userApplications = applicationService.getAllUserApplications(username);
		long openTicketsForApplications = 0, inprogressTicketsForApplication = 0, closedTicketsForApplications = 0;
		for (UserApplication userApplication : userApplications)
		{
			Long id = getAplicationId(userApplication);
			openTicketsForApplications += ticketService.getNumberOfTicketsforApplicationByState(id, TicketState.NEW);
			inprogressTicketsForApplication += ticketService.getNumberOfTicketsforApplicationByState(id, TicketState.INPROGRESS);
			closedTicketsForApplications += ticketService.getNumberOfTicketsforApplicationByState(id, TicketState.CLOSED);
		}
		List<TicketDTO> allRecentTickets = ticketService.getTicketsCreatedAfterDate(fromDate);
		Collection<TicketDTO> openTicketList = Collections2.filter(allRecentTickets, TICKET_STATE_PREDICATE_OPEN);
		Collection<TicketDTO> inProgressList = Collections2.filter(allRecentTickets, TICKET_STATE_PREDICATE_CLOSED);
		return new LandingDTO(openTicketsForApplications, inprogressTicketsForApplication, closedTicketsForApplications, allRecentTickets, new ArrayList<TicketDTO>(openTicketList),
				new ArrayList<TicketDTO>(inProgressList));

	}

	/**
	 * @param userApplication
	 * @return
	 */
	private Long getAplicationId(UserApplication userApplication)
	{
		Application application = userApplication.getApplication();
		Long id = application.getId();
		return id;
	}

	private static Predicate<TicketDTO> TICKET_STATE_PREDICATE_OPEN = new Predicate<TicketDTO>()
	{
		public boolean apply(TicketDTO ticket)
		{
			return ticket.getState().equalsIgnoreCase(TicketState.NEW.getState());
		}
	};

	private static Predicate<TicketDTO> TICKET_STATE_PREDICATE_CLOSED = new Predicate<TicketDTO>()
	{
		public boolean apply(TicketDTO ticket)
		{
			return ticket.getState().equalsIgnoreCase(TicketState.CLOSED.getState());
		}
	};
}
