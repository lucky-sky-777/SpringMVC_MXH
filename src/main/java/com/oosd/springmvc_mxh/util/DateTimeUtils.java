package com.oosd.springmvc_mxh.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
	public static String formatTimestamp(long timestamp, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		String formatted = Instant.ofEpochMilli(timestamp)
				.atZone(ZoneId.systemDefault())
				.format(formatter);

		return formatted;
	}
}