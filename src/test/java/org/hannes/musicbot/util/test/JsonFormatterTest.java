package org.hannes.musicbot.util.test;

import org.hannes.musicbot.util.JSONFormatter;
import org.junit.Test;

import com.google.gson.Gson;

public class JsonFormatterTest {

	/**
	 * Test input string received from a previous request
	 */
	private static final String TEST_INPUT = "info = {\"title\":\"Feint - No Chance\",\"image\":\"http://i.ytimg.com//vi/zl7FZGyFjqo//default.jpg\",\"length\":\"\",\"status\":\"serving\",\"progress_speed\":\"\",\"progress\":\"\",\"ads\":\"\",\"pf\":\"\",\"pc\":\"2838.vd.aclst.com\",\"h\":\"\",\"px\":\"\",\"ts_create\":1461927696,\"r\":\"OTQuMTQzLjE4OC4w\",\"h2\":\"4b0761deb882448dbefe78572ba4ff57\"};";

	/**
	 * Performs the formatter test on a JSON response we have gotten from the
	 * end point previously
	 */
	@Test
	public void testFormatter() {
		Gson gson = new Gson();
		gson.fromJson(JSONFormatter.format(TEST_INPUT), Object.class);
	}

}
