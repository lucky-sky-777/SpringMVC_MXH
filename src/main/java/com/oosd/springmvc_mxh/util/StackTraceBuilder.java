package com.oosd.springmvc_mxh.util;

public final class StackTraceBuilder {

	public static String build(StackTraceElement[] stackTraceElements) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean isFirstLine = true;
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			if (isFirstLine) {
				isFirstLine = false;
			} else {
				stringBuilder.append("\n");
			}
			stringBuilder.append(stackTraceElement.toString());
		}
		return stringBuilder.toString();
	}

}