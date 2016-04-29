package org.hannes.musicbot.controller;

import org.hannes.musicbot.App;
import org.hannes.musicbot.model.LookupResponse;
import org.hannes.musicbot.util.Bootstrap;
import org.hannes.musicbot.util.JSONFormatter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

@RestController
public class DownloadController {

	/**
	 * The template of the item info request url
	 */
	private static final String ITEM_INFO_TEMPLATE = "/a/itemInfo/?video_id=%s&ac=www&t=grp&r=%d";
	
	/**
	 * The static gson instance
	 */
	private static final Gson gson = new Gson();
	
	/**
	 * @param video_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lookup", method = RequestMethod.POST)
	public String update(@RequestParam(value="video_id") String video_id)  throws Exception {
		HttpResponse<String> httpResponse = Unirest.get(App.REMOTE_URL + Bootstrap.sig_url(String.format(ITEM_INFO_TEMPLATE, video_id, System.currentTimeMillis()))).asString();

		/*
		 * Gets the lookup response
		 */
		LookupResponse lookupResponse = gson.fromJson(JSONFormatter.format(httpResponse.getBody().toString()), LookupResponse.class);

		/*
		 * Returns the download link
		 */
		return lookupResponse.getDownloadUrl(video_id);
	}
	
}
