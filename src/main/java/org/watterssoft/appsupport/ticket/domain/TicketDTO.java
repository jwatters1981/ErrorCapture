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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.watterssoft.appsupport.application.domain.ApplicationDTO;
import org.watterssoft.appsupport.common.utils.DateFormatter;

import com.google.common.base.Preconditions;

/**
 * @author johnwatters 8 May 2014 12:18:22
 */

public class TicketDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String applicationName;
	private String ticketDescription;
	
	private List<TicketComment> ticketComments = new ArrayList<TicketComment>();
	private String createdDate;
	private Long ticketId;
	private String state;

	private ApplicationDTO applicationsHolder;
	private List<ApplicationDTO> applications = new ArrayList<ApplicationDTO>();
	private String ticketComment;

	

	public TicketDTO(Ticket ticket)
	{
		Preconditions.checkNotNull(ticket, "Ticket was null");
		Preconditions.checkNotNull(ticket.getApplication(), "Ticket should have an application");
		Preconditions.checkNotNull(ticket.getState(), "State should not be null");

		this.applicationName = ticket.getApplication().getName();
		this.ticketDescription = ticket.getDescription();
		this.applicationsHolder = new ApplicationDTO(ticket.getApplication());
		this.createdDate = DateFormatter.formatDateToString(ticket.getCreatedDate());
		this.ticketId = ticket.getId();
		this.state = ticket.getState().getState();
		this.ticketComments = ticket.getTicketComments();
		if(!this.ticketComments.isEmpty())
		{
			this.ticketComment = ticketComments.get(0).getComment();
		}
	}

	public TicketDTO(Ticket ticket, List<ApplicationDTO> applications)
	{
		this(ticket);
		this.applications = applications;
	}

	public TicketDTO(List<ApplicationDTO> applications)
	{
		this.applications = applications;
	}

	public TicketDTO()
	{

	}

	public String getApplicationName()
	{
		return applicationName;
	}

	public String getTicketDescription()
	{
		return ticketDescription;
	}

	public String getCreatedDate()
	{
		return createdDate;
	}

	public List<ApplicationDTO> getApplications()
	{
		return applications;
	}

	public Long getTicketId()
	{
		return ticketId;
	}

	public void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
	}

	public void setTicketDescription(String ticketDescription)
	{
		this.ticketDescription = ticketDescription;
	}

	public void setCreatedDate(String createdDate)
	{
		this.createdDate = createdDate;
	}

	public void setApplications(List<ApplicationDTO> applications)
	{
		this.applications = applications;
	}

	public ApplicationDTO getApplicationsHolder()
	{
		return applicationsHolder;
	}

	public void setApplicationsHolder(ApplicationDTO applicationsHolder)
	{
		this.applicationsHolder = applicationsHolder;
	}

	public String getState()
	{
		return this.state;
	}

	public String getTicketComment()
	{
		return ticketComment;
	}

	public void setTicketComment(String ticketComment)
	{
		this.ticketComment = ticketComment;
	}

	
	public List<TicketComment> getTicketComments()
	{
		return ticketComments;
	}

	public void setTicketComments(List<TicketComment> ticketComments)
	{
		this.ticketComments = ticketComments;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}

	public void setTicketId(Long ticketId)
	{
		this.ticketId = ticketId;
	}

}
