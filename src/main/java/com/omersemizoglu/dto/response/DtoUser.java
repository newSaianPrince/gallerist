package com.omersemizoglu.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUser extends DtoBase{

	private String username;
	
	private String password;
}
