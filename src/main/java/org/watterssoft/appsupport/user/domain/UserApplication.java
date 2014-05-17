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

package org.watterssoft.appsupport.user.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.watterssoft.appsupport.application.domain.Application;

/**
 * @author johnwatters 6 May 2014 13:06:43
 */
@Entity
@NamedQuery(name="UserApplication.findByUser",query="select p from UserApplication as p where p.user.username = :username")
public class UserApplication implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@JoinColumn(name = "USER_NAME")
	@ManyToOne
	private User user;

	@JoinColumn(name = "APPLICATION_NAME")
	@ManyToOne
	private Application application;

	public UserApplication()
	{
		
	}
	
	public UserApplication(User user, Application application) {
		super();
		this.user = user;
		this.application = application;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	
}
