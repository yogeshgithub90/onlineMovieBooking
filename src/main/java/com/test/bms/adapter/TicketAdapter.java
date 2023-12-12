/**
 * 
 */
package com.test.bms.adapter;

import com.test.bms.dto.TicketDto;
import com.test.bms.model.TicketEntity;

import lombok.experimental.UtilityClass;

/**
 * @author yogesh
 *
 * @date 04-Sep-2019
 */
@UtilityClass
public class TicketAdapter {

	public static TicketEntity toEntity(TicketDto ticketDto) {

		return TicketEntity.builder()
				.allottedSeats(ticketDto.getAllottedSeats())
				.amount(ticketDto.getAmount())
				.build();

	}

	public static TicketDto toDto(TicketEntity ticketEntity) {

		return TicketDto.builder()
				.id(ticketEntity.getId())
				.allottedSeats(ticketEntity.getAllottedSeats())
				.amount(ticketEntity.getAmount())
				.show(ShowAdapter.toDto(ticketEntity.getShow()))
				.build();
	}

}