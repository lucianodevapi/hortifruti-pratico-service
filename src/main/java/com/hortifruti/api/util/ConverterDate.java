package com.hortifruti.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterDate {

	public static Date stringToDate(String value) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date();
		try {
			data = formato.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
}
