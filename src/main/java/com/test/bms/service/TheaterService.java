/**
 * 
 */
package com.test.bms.service;

import com.test.bms.dto.TheaterDto;

/**
 * @author yogesh
 *
 * @date 05-Sep-2019
 */
public interface TheaterService {

	TheaterDto addTheater(TheaterDto theaterDto);

	TheaterDto getTheater(long id);
}