package org.hannes.musicbot.controller;

import org.hannes.musicbot.util.Bootstrap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashController {
	
	@RequestMapping(value = "/hash", method = RequestMethod.GET)
	public String update(@RequestParam(value="url") String url)  throws Exception {
		return Bootstrap.sig_url(url);
	}

}