package org.hannes.musicbot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.script.ScriptException;

import org.hannes.musicbot.util.Bootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	/**
	 * Application entry point 
	 * 
	 * @param args
	 * @throws ScriptException
	 * @throws URISyntaxException
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws ScriptException, URISyntaxException, MalformedURLException, IOException {
		/*
		 * Load the javascript file for generating the hash code of the url
		 */
		Bootstrap.load();
		
		/*
		 * Start the spring boot application
		 */
		SpringApplication.run(App.class, args);
	}

}
