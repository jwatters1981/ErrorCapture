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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.watterssoft.appsupport.application.dao.ApplicationDao;
import org.watterssoft.appsupport.application.domain.Application;
import org.watterssoft.appsupport.ticket.dao.TicketCommentDao;
import org.watterssoft.appsupport.ticket.dao.TicketDao;
import org.watterssoft.appsupport.ticket.domain.Priority;
import org.watterssoft.appsupport.ticket.domain.Ticket;
import org.watterssoft.appsupport.ticket.domain.TicketComment;
import org.watterssoft.appsupport.ticket.domain.TicketDTO;
import org.watterssoft.appsupport.ticket.domain.TicketState;

import com.google.common.base.Preconditions;

/**
 * @author johnwatters Apr 29, 2014 10:55:25 PM
 */
@Service
public class TicketService
{

	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private TicketCommentDao ticketCommentDao;

	@Autowired
	private ApplicationDao applicationDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Ticket getTicketById(long ticketId)
	{
		return ticketDao.findById(ticketId);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TicketDTO> getAllTicketsForApplicationReadOnly(Long application, PageRequest pageable)
	{
		List<Ticket> list = getAllTicketsForApplication(application, pageable);
		List<TicketDTO> ticketDTOs = convertTicketListToTicketDTOList(list);
		return ticketDTOs;
	}

	/**
	 * @param list
	 * @return
	 */
	private List<TicketDTO> convertTicketListToTicketDTOList(List<Ticket> list)
	{
		List<TicketDTO> ticketDTOs = new ArrayList<TicketDTO>();
		for (Ticket ticket : list)
		{
			ticketDTOs.add(new TicketDTO(ticket));
		}
		return ticketDTOs;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
 	public List<TicketDTO> getTicketsCreatedAfterDate(Date date)
	{
		Preconditions.checkNotNull(date);
		List<Ticket> tickets  =  ticketDao.findNumberOfTicketsCreatedAfterDate(date);
		List<TicketDTO> ticketDTOs = convertTicketListToTicketDTOList(tickets);
		return ticketDTOs;
	}

	public void addTicket(Ticket ticket)
	{
		ticketDao.save(ticket);
	}
	
	@Transactional
	public Long getNumberOfTicketsforApplication(long applicationId)
	{
		return ticketDao.countTicketsForApplication(applicationId);
	}
	
	@Transactional
	public Long getNumberOfTicketsforApplicationByState(long applicationId, TicketState state)
	{
		return ticketDao.countTicketsForApplicationByState(applicationId, state.name());
	}

	@Transactional
	public void convertAndSaveTicketDTO(TicketDTO ticketDTO, String username)
	{
		Application application = applicationDao.findApplicationById(ticketDTO.getApplicationsHolder().getId());
		Ticket ticket = new Ticket(ticketDTO.getTicketDescription(), new Date(), Priority.P1, application);
		TicketComment ticketComment = new TicketComment(ticket,ticketDTO.getTicketComment(),username,new Date());
		ticketCommentDao.save(ticketComment);
		ticketDao.save(ticket);
	}

	public void removeTicket(Ticket ticket)
	{
		ticketDao.delete(ticket);
	}

	public List<Ticket> getAllTicketsForApplication(Long application, PageRequest pageable)
	{
		return ticketDao.findTicketsByApplication(application, pageable);
	}

	public void setTicketDao(TicketDao ticketDao)
	{
		this.ticketDao = ticketDao;
	}

}
