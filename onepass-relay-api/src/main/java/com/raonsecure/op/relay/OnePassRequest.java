package com.raonsecure.op.relay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OnePassRequest  {
	private String command;
	private String bizReqType;
	private String svcId;
	private String svcTrId;
	private String siteId;
	private String loginId;
	private String deviceId;
	private String appId;
	
	
	public OnePassRequest() {
		
	}
	
//	public OnePassRequest(OnePassRelayRequest relay_req) {
//		this.deviceId = relay_req.getDeviceId();
//		this.loginId  = relay_req.getLoginId();
//		this.appId    = relay_req.getAppId();	
//		this.bizReqType = "server";
//	}
	public String getBizReqType() {
		return bizReqType;
	}
	public void setBizReqType(String bizReqType) {
		this.bizReqType = bizReqType;
	}
	public String getSvcTrId() {
		return svcTrId;
	}
	public void setSvcTrId(String svcTrId) {
		this.svcTrId = svcTrId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
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
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getSvcId() {
		return svcId;
	}
	public void setSvcId(String svcId) {
		this.svcId = svcId;
	}
	
	public String toJsonString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
	public String toString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

}
