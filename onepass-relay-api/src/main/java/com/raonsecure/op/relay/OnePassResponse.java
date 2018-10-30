package com.raonsecure.op.relay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OnePassResponse  {
	private String resultCode;
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public OnePassResult getResultData() {
		return resultData;
	}

	public void setResultData(OnePassResult resultData) {
		this.resultData = resultData;
	}

	private String resultMsg;
	private OnePassResult resultData;
	
	public static OnePassResponse fromJson(String payload) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(payload, OnePassResponse.class);
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
