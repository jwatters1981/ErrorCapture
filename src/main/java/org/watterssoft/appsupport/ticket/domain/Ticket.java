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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.watterssoft.appsupport.application.domain.Application;

/**
 * @author johnwatters Apr 29, 2014 10:55:52 PM
 */
@Entity
@NamedNativeQueries({
		@NamedNativeQuery(name = "Ticket.countNumberOfTicketsForApplicationByState", query = "select count(ID) from ticket s where s.application_id = :application_id and ticket_state = :state"),
		@NamedNativeQuery(name = "Ticket.countNumberOfTicketsForApplication", query = "select count(ID) from ticket s where s.application_id = :application_id") })
@NamedQueries({ @NamedQuery(name = "Ticket.findNumberOfTicketsCreatedAfterDate", query = "select s from Ticket s where s.createdDate >= :date") })
public class Ticket implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@Column(name = "ticket_name")
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate = new Date();

	@Column(name = "ticket_priority")
	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Column(name = "ticket_state")
	@Enumerated(EnumType.STRING)
	private TicketState state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLICATION_ID")
	private Application application;

	public Ticket(String description, Date createdDate, Priority priority, Application application)
	{
		this(description, createdDate, priority, TicketState.NEW, application);
	}

	public Ticket(String description, Date createdDate, Priority priority, TicketState state, Application application)
	{
		super();
		this.description = description;
		this.createdDate = createdDate;
		this.priority = priority;
		this.state = state;
		this.application = application;
	}

	public Ticket()
	{
		super();
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Priority getPriority()
	{
		return priority;
	}

	public void setPriority(Priority priority)
	{
		this.priority = priority;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Application getApplication()
	{
		return application;
	}

	public void setApplication(Application application)
	{
		this.application = application;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}

	public TicketState getState()
	{
		return state;
	}

	public void setState(TicketState state)
	{
		this.state = state;
	}

}
