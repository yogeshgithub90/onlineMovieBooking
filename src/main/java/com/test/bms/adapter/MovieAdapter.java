/**
 * 
 */
package com.test.bms.adapter;

import com.test.bms.dto.MovieDto;
import com.test.bms.model.MovieEntity;

import lombok.experimental.UtilityClass;

/**
 * @author yogesh
 *
 * @date 04-Sep-2019
 */
@UtilityClass
public class MovieAdapter {

	public static MovieEntity toEntity(MovieDto movieDto) {

		return MovieEntity.builder()
				.name(movieDto.getName())
				.language(movieDto.getLanguage())
				.certificateType(movieDto.getCertificateType())
				.releaseDate(movieDto.getReleaseDate())
				.build();

	}

	public static MovieDto toDto(MovieEntity movieEntity) {

		return MovieDto.builder()
				.id(movieEntity.getId())
				.name(movieEntity.getName())
				.language(movieEntity.getLanguage())
				.certificateType(movieEntity.getCertificateType())
				.releaseDate(movieEntity.getReleaseDate())
				.build();
	}

}