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

package org.watterssoft.appsupport.application.domain;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Preconditions;

/**
 * @author johnwatters 7 May 2014 08:45:03
 */

public class ApplicationDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String name;
	private String version;
	private String url;
	private long id;
	private int numberOfOpenTickets = 0, numberOfClosedTickets = 0;
	private Date lastDateTicketCreated = new Date();
	private String applicationsHolder;

	public ApplicationDTO()
	{

	}

	public ApplicationDTO(Application application)
	{
		this.name = application.getName();
		this.version = application.getVersion();
		Preconditions.checkNotNull(application.getId(), "Application Id cannot be null");
		this.id = application.getId();
		this.url = application.getUrl();
	}
	
	public ApplicationDTO(Application application, int numberOfOpenTickets, int numberOfClosedTickets, Date lastDateTicketCreated)
	{
		this.name = application.getName();
		this.version = application.getVersion();
		this.id = application.getId();
		this.numberOfClosedTickets = numberOfClosedTickets;
		this.numberOfOpenTickets = numberOfOpenTickets;
		this.lastDateTicketCreated = lastDateTicketCreated;
	}

	public String getName()
	{
		return name;
	}

	public String getVersion()
	{
		return version;
	}

	public long getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getNumberOfOpenTickets()
	{
		return numberOfOpenTickets;
	}

	public int getNumberOfClosedTickets()
	{
		return numberOfClosedTickets;
	}

	public Date getLastDateTicketCreated()
	{
		return lastDateTicketCreated;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getApplicationsHolder()
	{
		return applicationsHolder;
	}

	public void setApplicationsHolder(String applicationsHolder)
	{
		this.applicationsHolder = applicationsHolder;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationsHolder == null) ? 0 : applicationsHolder.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ApplicationDTO other = (ApplicationDTO) obj;
		if (applicationsHolder == null)
		{
			if (other.applicationsHolder != null)
				return false;
		}
		else if (!applicationsHolder.equals(other.applicationsHolder))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
