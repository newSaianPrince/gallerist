package com.omersemizoglu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.omersemizoglu.dto.response.DtoUser;
import com.omersemizoglu.auth.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "password", ignore = true)
	DtoUser toDto(User user);
}