package org.hannes.musicbot.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import javax.script.ScriptException;

import org.hannes.musicbot.App;
import org.hannes.musicbot.model.LookupResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class MusicbotIntegrationTest {

	/**
	 * 
	 */
	private static final String ITEM_INFO_TEMPLATE = "/a/itemInfo/?video_id=%s&ac=www&t=grp&r=%d";

	/**
	 * The test video id
	 */
	private static final String VIDEO_ID = "fCvNcZmcEb0";

	/**
	 * The static gson instance
	 */
	private static final Gson gson = new Gson();

	@BeforeClass
	public static void initialize() throws FileNotFoundException, ScriptException, URISyntaxException {
		Bootstrap.load();
	}

	@Test
	public void integrationTest() throws Exception {
		HttpResponse<String> httpResponse = Unirest.get(App.REMOTE_URL + Bootstrap.sig_url(String.format(ITEM_INFO_TEMPLATE, VIDEO_ID, System.currentTimeMillis()))).asString();

		/*
		 * Gets the lookup response
		 */
		LookupResponse lookupResponse = gson.fromJson(JSONFormatter.format(httpResponse.getBody().toString()), LookupResponse.class);

		/*
		 * Return the fixed JSON format
		 */
		assertNotNull(lookupResponse.getDownloadId());
		assertNotNull(lookupResponse.getSessionId());
		assertNotNull(lookupResponse.getTimeCreated());
		
		/*
		 * Gets the download link
		 */
		URL url = new URL(lookupResponse.getDownloadUrl(VIDEO_ID));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.8,nl;q=0.6");
		connection.setRequestProperty("Connection", "keep-alive");
		connection.setRequestProperty("Host", "www.youtube-mp3.org");
		connection.setRequestProperty("Referer", "http://www.youtube-mp3.org/");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36");
		connection.setRequestProperty("Upgrade-Insecure-Requests", "1");

		/*
		 * Sees if the resource exists
		 */
		assertEquals(200, connection.getResponseCode());
	}

}
