/**
 * 
 */
package com.test.bms.dto;

import java.time.LocalDate;

import com.test.bms.enums.CertificateType;
import com.test.bms.enums.MovieLanguage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yogesh
 *
 * @date 05-Sep-2019
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class MovieDto {

	private long id;

	private String name;

	private MovieLanguage language;

	private CertificateType certificateType;

	private LocalDate releaseDate;

}