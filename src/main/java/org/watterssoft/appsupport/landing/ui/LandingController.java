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

package org.watterssoft.appsupport.landing.ui;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.watterssoft.appsupport.landing.domain.LandingDTO;
import org.watterssoft.appsupport.landing.service.LandingService;

/**
 * @author johnwatters 16 May 2014 20:55:08
 */
@Controller
@RequestMapping(value = "/landing")
public class LandingController
{
	@Autowired
	private LandingService landingService;
	

	@RequestMapping("/landingData")
	public @ResponseBody LandingDTO loadLandingDTO(HttpServletRequest request)
	{
		return landingService.getLandingPageForUser(request.getRemoteUser());
	}
	@RequestMapping("/viewLanding")
	public String getViewLandingPartial()
	{
		return "landing/viewLanding";
	}
}
