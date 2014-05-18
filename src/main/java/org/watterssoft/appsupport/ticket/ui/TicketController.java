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

package org.watterssoft.appsupport.ticket.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.watterssoft.appsupport.application.domain.ApplicationDTO;
import org.watterssoft.appsupport.application.service.ApplicationService;
import org.watterssoft.appsupport.ticket.domain.Ticket;
import org.watterssoft.appsupport.ticket.domain.TicketDTO;
import org.watterssoft.appsupport.ticket.service.TicketService;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * @author johnwatters Apr 29, 2014 10:51:56 PM
 */
@Controller
@RequestMapping("/tickets")
public class TicketController
{

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ApplicationService applicationService;

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(HttpMessageNotReadableException e)
	{
		throw new RuntimeException("400 error in TicketController",e);
	}

	@RequestMapping("/ticketlist.json/{applicationId}/{start}/{end}")
	public @ResponseBody List<TicketDTO> getTicketList(@PathVariable String applicationId, @PathVariable Integer start, @PathVariable Integer end)
	{
		Preconditions.checkNotNull(applicationId);
		start = Objects.firstNonNull(start, new Integer(0));
		end = Objects.firstNonNull(end, new Integer(10));
		return ticketService.getAllTicketsForApplicationReadOnly(Long.valueOf(applicationId), new PageRequest(start, end));
	}

	@RequestMapping(value = "/addTicket", method = RequestMethod.POST)
	public String addTicket(@RequestBody TicketDTO ticketDTO, HttpServletRequest request)
	{
		ticketService.convertAndSaveTicketDTO(ticketDTO, request.getRemoteUser());
		return getTicketPartialPage();
	}

	@RequestMapping(value = "/viewTicket/{ticketId}", method = RequestMethod.GET)
	public @ResponseBody TicketDTO viewTicket(HttpServletRequest request, @PathVariable Long ticketId)
	{
		Ticket ticket = ticketService.getTicketById(ticketId);
		List<ApplicationDTO> applications = applicationService.getAllUserApplicationDTO(request.getRemoteUser());
		return new TicketDTO(ticket, applications);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public @ResponseBody TicketDTO newTicket(HttpServletRequest request)
	{
		List<ApplicationDTO> applications = applicationService.getAllUserApplicationDTO(request.getRemoteUser());
		return new TicketDTO(applications);
	}

	@RequestMapping(value = "/newBlankTicket", method = RequestMethod.GET)
	public String createBlankTicket()
	{
		return "tickets/newTicket";
	}

	@RequestMapping("/layout")
	public String getTicketPartialPage()
	{
		return "tickets/layout";
	}

	@RequestMapping("/updateTicket")
	public String getUpdateTicketPartialPage()
	{
		return "tickets/updateTicket";
	}
}
