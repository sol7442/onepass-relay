package com.raonsecure.op.relay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OnePassRelayRequest {
	private String loginId;
	private String deviceId;
	
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

	public OenPassRequest getRequest() {
		return null;
	}
	
	public String toJsonString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public static OnePassRelayRequest fromJson(String payload) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(payload, OnePassRelayRequest.class);
	}
}
