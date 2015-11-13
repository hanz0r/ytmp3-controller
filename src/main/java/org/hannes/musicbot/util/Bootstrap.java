package org.hannes.musicbot.util;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Bootstrap {
	
	/**
	 * The class responsible for all of the script engines
	 */
	private static final ScriptEngineManager engineManager = new ScriptEngineManager();
	
	/**
	 * The script engine
	 */
	private static final ScriptEngine engine = engineManager.getEngineByName("javascript");

	/**
	 * 
	 * @param uri
	 * @throws ScriptException
	 * @throws FileNotFoundException 
	 */
	public static void load() throws ScriptException, FileNotFoundException {
		engine.eval(new InputStreamReader(ClassLoader.getSystemResourceAsStream("client.js")));
	}

	/**
	 * Calls the Javascript function that will hash the given url
	 * 
	 * @param url
	 * @return
	 * @throws ScriptException 
	 * @throws NoSuchMethodException 
	 * @throws Exception
	 */
	public static long sig(String url) throws NoSuchMethodException, ScriptException {
		return (long) ((double) callFunction("sig", url));
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws NoSuchMethodException
	 * @throws ScriptException
	 */
	public static String sig_url(String url) throws NoSuchMethodException, ScriptException {
		return callFunction("sig_url", url);
	}
	
	/**
	 * 
	 * @param param
	 * @return
	 * @throws ScriptException 
	 * @throws FileNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static <T> T callFunction(String name, Object... params) throws NoSuchMethodException, ScriptException {
		return (T) ((Invocable) engine).invokeFunction(name, params);
	}

}