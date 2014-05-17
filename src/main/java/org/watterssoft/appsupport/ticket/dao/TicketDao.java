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

package org.watterssoft.appsupport.ticket.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.watterssoft.appsupport.ticket.domain.Ticket;

/**
 * @author johnwatters 5 May 2014 14:52:57
 */
@Transactional
@org.springframework.stereotype.Repository
public interface TicketDao extends Repository<Ticket, Long>
{

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Ticket findById(Long id);

	public Ticket save(Ticket ticket);

	public void delete(Ticket ticket);

	@Query("select p from Ticket as p where p.application.id = :application")
	public List<Ticket> findTicketsByApplication(@Param("application") Long application, Pageable pageable);

	@Query(name = "Ticket.countNumberOfTicketsForApplication")
	public Long countTicketsForApplication(@Param("application_id") long application);

	@Query(name = "Ticket.countNumberOfTicketsForApplicationByState")
	public Long countTicketsForApplicationByState(@Param("application_id") long application, @Param("state") String state);
	
	@Query(name = "Ticket.findNumberOfTicketsCreatedAfterDate")
	public List<Ticket> findNumberOfTicketsCreatedAfterDate(@Param("date")Date date);

}
