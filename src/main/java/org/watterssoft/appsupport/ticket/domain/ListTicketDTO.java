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

package org.watterssoft.appsupport.ticket.domain;

import java.util.Date;

/**
 * @author johnwatters
 * 20 May 2014 08:17:32
 */

public class ListTicketDTO
{
	private String applicationName;
	private String ticketDescription;

	private Date createdDate;
	private Long ticketId;
	private String state;

	public ListTicketDTO()
	{
		super();
	}
	
	public ListTicketDTO(Ticket ticket)
	{
		this(ticket.getApplication().getName(), ticket.getDescription(), ticket.getCreatedDate(), ticket.getId(), ticket.getState().name());
	}

	public ListTicketDTO(String applicationName, String ticketDescription, Date createdDate, Long ticketId, String state)
	{
		super();
		this.applicationName = applicationName;
		this.ticketDescription = ticketDescription;
		this.createdDate = createdDate;
		this.ticketId = ticketId;
		this.state = state;
	}

	public String getApplicationName()
	{
		return applicationName;
	}

	public void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
	}

	public String getTicketDescription()
	{
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription)
	{
		this.ticketDescription = ticketDescription;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Long getTicketId()
	{
		return ticketId;
	}

	public void setTicketId(Long ticketId)
	{
		this.ticketId = ticketId;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
}
