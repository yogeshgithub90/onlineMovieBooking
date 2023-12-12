/**
 * 
 */
package com.test.bms.service;

import com.test.bms.dto.BookTicketRequestDto;
import com.test.bms.dto.TicketDto;

/**
 * @author yogesh
 *
 * @date 05-Sep-2019
 */
public interface TicketService {

	TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto);

	TicketDto getTicket(long id);
}