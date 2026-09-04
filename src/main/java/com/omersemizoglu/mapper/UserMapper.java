package com.omersemizoglu.mapper;

import org.springframework.stereotype.Component;

import com.omersemizoglu.auth.model.User;
import com.omersemizoglu.dto.response.DtoUser;

@Component
public class UserMapper {

	public DtoUser toDto(User user) {
		DtoUser dto = new DtoUser();
		dto.setId(user.getId());
		dto.setCreateTime(user.getCreateTime());
		dto.setUsername(user.getUsername());
		return dto;
	}
}
