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

package org.watterssoft.appsupport.application.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.watterssoft.appsupport.application.domain.Application;
import org.watterssoft.appsupport.application.domain.ApplicationDTO;
import org.watterssoft.appsupport.application.service.ApplicationService;
import org.watterssoft.appsupport.user.service.UserService;

/**
 * @author johnwatters Apr 25, 2014 9:38:58 PM
 */
@Controller
@RequestMapping("/application")
public class ApplicationController
{

	private UserService userService;

	private ApplicationService applicationService;


	@Autowired
	public ApplicationController(UserService userService, ApplicationService applicationService)
	{
		super();
		this.userService = userService;
		this.applicationService = applicationService;
	}

	@RequestMapping("/list")
	public @ResponseBody List<ApplicationDTO> getUserApplications(HttpServletRequest request)
	{
		return applicationService.getAllUserApplicationDTO(request.getRemoteUser());
	}

	@RequestMapping(value = "/addApplication", method = RequestMethod.POST)
	public @ResponseBody void addApplication(@RequestBody ApplicationDTO applicationDTO, HttpServletRequest request)
	{
		Application application = new Application(applicationDTO.getName(), applicationDTO.getVersion(), applicationDTO.getUrl());
		application = applicationService.save(application, userService.getUserByUserName(request.getRemoteUser()));
		applicationDTO.setId(application.getId());
	}

	@RequestMapping(value = "/newApp", method = RequestMethod.GET)
	public @ResponseBody ApplicationDTO newApplication(HttpServletRequest request)
	{
		return new ApplicationDTO();
	}

	@RequestMapping(value = "/newBlankApplication", method = RequestMethod.GET)
	public String createBlankTicket()
	{
		return "application/newApplication";
	}

	@RequestMapping("/layout")
	public String getTicketPartialPage()
	{
		return "application/layout";
	}


}
