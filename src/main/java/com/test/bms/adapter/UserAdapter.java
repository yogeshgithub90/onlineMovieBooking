/**
 * 
 */
package com.test.bms.adapter;

import com.test.bms.dto.UserDto;
import com.test.bms.model.UserEntity;

import lombok.experimental.UtilityClass;

/**
 * @author yogesh
 *
 * @date 04-Sep-2019
 */
@UtilityClass
public class UserAdapter {

	public static UserEntity toEntity(UserDto userDto) {

		return UserEntity.builder()
				.name(userDto.getName())
				.mobile(userDto.getMobile())
				.build();

	}

	public static UserDto toDto(UserEntity userEntity) {

		return UserDto.builder()
				.id(userEntity.getId())
				.name(userEntity.getName())
				.mobile(userEntity.getMobile())
				.build();
	}

}