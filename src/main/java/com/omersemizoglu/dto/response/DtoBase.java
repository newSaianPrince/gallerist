package com.omersemizoglu.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoBase {

	private Long id;
	
	private LocalDateTime createTime;
}