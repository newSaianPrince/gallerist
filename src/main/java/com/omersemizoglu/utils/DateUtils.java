package com.omersemizoglu.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public static String getCurrentDate(LocalDate date) {
		return date.format(DATE_FORMATTER);
	}

	public static String getCurrentDate(LocalDateTime dateTime) {
		return dateTime.toLocalDate().format(DATE_FORMATTER);
	}
}