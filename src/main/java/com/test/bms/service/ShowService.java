/**
 * 
 */
package com.test.bms.service;

import java.time.LocalDate;
import java.time.LocalTime;

import com.test.bms.dto.PageResponse;
import com.test.bms.dto.ShowDto;

/**
 * @author yogesh
 *
 * @date 04-Sep-2019
 */
public interface ShowService {

	ShowDto addShow(ShowDto showDto);

	PageResponse<ShowDto> searchShows(String movieName, String city, LocalDate showDate, LocalTime showTime, int pageNo, int limit);

}