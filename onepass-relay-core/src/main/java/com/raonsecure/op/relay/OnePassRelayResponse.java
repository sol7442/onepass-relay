package com.raonsecure.op.relay;

public class OnePassRelayResponse extends JsonMessage {
	private String resultCode;
	private String resultMsg;
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

	private OnePassResult resultData;
	
	public OnePassRelayResponse(OnePassResponse res) {
		this.resultCode = res.getResultCode();
		this.resultMsg  = res.getResultMsg();
		this.resultData = res.getResultData();
	}
}
