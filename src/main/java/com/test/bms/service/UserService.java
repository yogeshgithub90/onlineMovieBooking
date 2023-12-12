/**
 * 
 */
package com.test.bms.service;

import com.test.bms.dto.UserDto;

/**
 * @author yogesh
 *
 * @date 05-Sep-2019
 */
public interface UserService {

	UserDto addUser(UserDto userDto);

	UserDto getUser(long id);
}