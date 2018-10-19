package com.raonsecure.op.relay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OnePassRelayRequest extends JsonMessage {
	private String loginId;
	private String deviceId;
	private String appId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public static OnePassRelayRequest fromJson(String payload) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(payload, OnePassRelayRequest.class);
	}
}
