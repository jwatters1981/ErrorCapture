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

package org.watterssoft.appsupport.landing.domain;

import java.util.List;

import org.watterssoft.appsupport.ticket.domain.TicketDTO;

/**
 * @author johnwatters 16 May 2014 22:24:37
 */

public class LandingDTO
{

	long openTickets, inprogressTickets, closedTickets;
	private List<TicketDTO> recentTickets, openTicketsList, closedTicketsList;
	public LandingDTO(long openTickets, long inprogressTickets, long closedTickets, List<TicketDTO> recentTickets, List<TicketDTO> openTicketsList, List<TicketDTO> closedTicketsList)
	{
		super();
		this.openTickets = openTickets;
		this.inprogressTickets = inprogressTickets;
		this.closedTickets = closedTickets;
		this.recentTickets = recentTickets;
		this.openTicketsList = openTicketsList;
		this.closedTicketsList = closedTicketsList;
	}
	
	public LandingDTO()
	{
		super();
	}
	public long getOpenTickets()
	{
		return openTickets;
	}
	public long getInprogressTickets()
	{
		return inprogressTickets;
	}
	public long getClosedTickets()
	{
		return closedTickets;
	}
	public List<TicketDTO> getRecentTickets()
	{
		return recentTickets;
	}
	public List<TicketDTO> getOpenTicketsList()
	{
		return openTicketsList;
	}
	public List<TicketDTO> getClosedTicketsList()
	{
		return closedTicketsList;
	}

	
}
