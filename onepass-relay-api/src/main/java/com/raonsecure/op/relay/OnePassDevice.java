package com.raonsecure.op.relay;

public class OnePassDevice {
    private String aaid;					
    private String verificationType;					
    private String verificationNm;
    private String vendorNm;				
    private String vendorId;
    
    public String getAaid() {
		return aaid;
	}
	public void setAaid(String aaid) {
		this.aaid = aaid;
	}
	public String getVerificationType() {
		return verificationType;
	}
	public void setVerificationType(String verificationType) {
		this.verificationType = verificationType;
	}
	public String getVerificationNm() {
		return verificationNm;
	}
	public void setVerificationNm(String verificationNm) {
		this.verificationNm = verificationNm;
	}
	public String getVendorNm() {
		return vendorNm;
	}
	public void setVendorNm(String vendorNm) {
		this.vendorNm = vendorNm;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
}
