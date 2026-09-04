package com.omersemizoglu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

	public static String getCurrentDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(date);
	}
}
