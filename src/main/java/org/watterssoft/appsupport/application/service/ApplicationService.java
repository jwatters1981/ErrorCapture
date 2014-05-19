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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.watterssoft.appsupport.application.dao.ApplicationDao;
import org.watterssoft.appsupport.application.domain.Application;
import org.watterssoft.appsupport.application.domain.ApplicationDTO;
import org.watterssoft.appsupport.ticket.dao.TicketDao;
import org.watterssoft.appsupport.ticket.domain.TicketState;
import org.watterssoft.appsupport.user.dao.IUserApplicationDao;
import org.watterssoft.appsupport.user.domain.User;
import org.watterssoft.appsupport.user.domain.UserApplication;

/**
 * @author johnwatters Apr 25, 2014 9:35:09 PM
 */
@Component
public class ApplicationService
{

	private ApplicationDao applicationDao;
	private IUserApplicationDao userApplicationDao;
	private TicketDao ticketDao;

	@Autowired
	public ApplicationService(ApplicationDao applicationDao, IUserApplicationDao userApplicationDao, TicketDao ticketDao)
	{
		this.applicationDao = applicationDao;
		this.userApplicationDao = userApplicationDao;
		this.ticketDao = ticketDao;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<UserApplication> getAllUserApplications(String username)
	{
		return userApplicationDao.findUserApplicationByUserName(username);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Application save(Application application, User user)
	{
		application = applicationDao.save(application);
		UserApplication userApplication = new UserApplication(user, application);
		userApplicationDao.save(userApplication);
		return application;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<ApplicationDTO> getAllUserApplicationDTO(String username)
	{
		List<UserApplication> allUserApplications = getAllUserApplications(username);
		List<ApplicationDTO> applicationDTOList = new ArrayList<ApplicationDTO>();
		for (UserApplication userApplication : allUserApplications)
		{
			Application application = userApplication.getApplication();
			Long numberOfOpenTickets = ticketDao.countTicketsForApplicationByState(application.getId(), TicketState.NEW.name());
			Long numberOfClosedTickets = ticketDao.countTicketsForApplicationByState(application.getId(), TicketState.CLOSED.name());

			ApplicationDTO applicationDTO = new ApplicationDTO(application, Long.valueOf(numberOfOpenTickets).intValue(), Long.valueOf(numberOfClosedTickets).intValue(), new Date());
			applicationDTOList.add(applicationDTO);
		}
		return applicationDTOList;
	}
}
