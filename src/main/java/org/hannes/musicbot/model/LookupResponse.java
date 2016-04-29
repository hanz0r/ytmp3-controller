package org.hannes.musicbot.model;

import org.hannes.musicbot.App;
import org.hannes.musicbot.util.Bootstrap;

import com.google.gson.annotations.SerializedName;

public class LookupResponse {

	public static final String TEMPLATE = "/get?video_id=%s&ts_create=%s&r=%s&h2=%s";

	/**
	 * The lookup response
	 */
	@SerializedName("r")
	private String downloadId;
	
	/**
	 * The time the initial request was made (in ms)
	 */
	@SerializedName("ts_create")
	private String timeCreated;
	
	/**
	 * The session id
	 */
	@SerializedName("h2")
	private String sessionId;
	
	/**
	 * Gets the download link from the received 
	 * @param videoId
	 * @return
	 */
	public String getDownloadUrl(String videoId) throws Exception {
		return App.REMOTE_URL + Bootstrap.sig_url(String.format(TEMPLATE, videoId, timeCreated, downloadId, sessionId));
	}

	/**
	 * @return the downloadId
	 */
	public String getDownloadId() {
		return downloadId;
	}

	/**
	 * @param downloadId the downloadId to set
	 */
	public void setDownloadId(String downloadId) {
		this.downloadId = downloadId;
	}
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the timeCreated
	 */
	public String getTimeCreated() {
		return timeCreated;
	}

	/**
	 * @param timeCreated the timeCreated to set
	 */
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

}
