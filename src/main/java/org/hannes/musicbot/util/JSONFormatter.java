package org.hannes.musicbot.util;

public class JSONFormatter {

	public static final String format(String input) {
		return input.substring(input.indexOf("{"), input.lastIndexOf("}") + 1);
	}

}
