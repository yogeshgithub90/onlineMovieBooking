/**
 * 
 */
package com.test.bms.service;

import com.test.bms.dto.MovieDto;

/**
 * @author yogesh
 *
 * @date 05-Sep-2019
 */
public interface MovieService {

	MovieDto addMovie(MovieDto movieDto);

	MovieDto getMovie(long id);
}