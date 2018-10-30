package com.raonsecure.op.relay;

public class OnePassResult {
	private String trId;
	private String expireTime;
	private String trStatus;
	public String getTrId() {
		return trId;
	}
	public void setTrId(String trId) {
		this.trId = trId;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getTrStatus() {
		return trStatus;
	}
	public void setTrStatus(String trStatus) {
		this.trStatus = trStatus;
	}
	public String getTrStatusMsg() {
		return trStatusMsg;
	}
	public void setTrStatusMsg(String trStatusMsg) {
		this.trStatusMsg = trStatusMsg;
	}
	public OnePassDevice[] getAaidAllowList() {
		return aaidAllowList;
	}
	public void setAaidAllowList(OnePassDevice[] aaidAllowList) {
		this.aaidAllowList = aaidAllowList;
	}
	private String trStatusMsg;
	private OnePassDevice[] aaidAllowList;
}
