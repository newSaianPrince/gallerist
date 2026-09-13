package com.omersemizoglu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.omersemizoglu.dto.request.DtoAccountIU;
import com.omersemizoglu.dto.response.DtoAccount;
import com.omersemizoglu.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	DtoAccount toDto(Account account);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createTime", ignore = true)
	Account toEntity(DtoAccountIU dtoAccountIU);
}