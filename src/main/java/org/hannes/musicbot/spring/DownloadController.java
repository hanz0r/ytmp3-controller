package org.hannes.musicbot.spring;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.hannes.musicbot.util.Bootstrap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class DownloadController {
	
	/**
	 * 
	 */
	private static final Gson gson = new Gson();
	
	/**
	 * 
	 */
	private static final String base_url = "http://www.youtube-mp3.org";
	
	@RequestMapping(value = "/lookup", method = RequestMethod.GET)
	public String update(@RequestParam(value="video_id", defaultValue="tD4HCZe-tew") String video_id)  throws Exception {
		String url = base_url + Bootstrap.sig_url("/a/itemInfo/?video_id=" + video_id + "&ac=www&t=grp&r=" + System.currentTimeMillis());
		
		/*
		 * Read the malformed json response
		 */
		StringBuilder json_builder = new StringBuilder();
		try (Scanner scanner = new Scanner(new URL(url).openStream())) {
			while (scanner.hasNext()) json_builder.append(scanner.nextLine());
		}
		
		/*
		 * www.youtube-mp3.org gives back faulty JSON data, need to remove "info = " at the start and remove the ; at the end
		 */
		json_builder.delete(0, 7).deleteCharAt(json_builder.length() - 1);
		
		/*
		 * Parse the JSON to a hashmap
		 */
		Map<String, Object> response = gson.fromJson(json_builder.toString(), new TypeToken<Map<String, Object>>() {}.getType());
		
		/*
		 * Return the fixed JSON format
		 */
		return base_url + Bootstrap.sig_url("/get?video_id=" + video_id + "&type=video&ts_create=" + (long) ((double) response.get("ts_create")) + "&r=" + response.get("r") + "&h2=" + response.get("h2"));
	}
	
}
