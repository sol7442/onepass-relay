package com.raonsecure.op;

import org.junit.Before;
import org.junit.Test;

import com.raonsecure.op.relay.OnePassRelayClient;
import com.raonsecure.op.relay.OnePassRelayManager;
import com.raonsecure.op.relay.OnePassResponse;

public class OnePassRelayServiceCallTest {

	private String serverUrl  = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do"; 
	private String siteId 	 = "TEST0000000000";
    private String serviceId = "HC0000000000";
    private String AppID     = "android:apk-key-hash:Df+2X53Z0UscvUu6obxC3rIfFyk";
    
    private String user_id = "userId";
	private String device_id = "gYJKoZIhvcNAQcCoIIH7zCCB+sCAQExDzANBglghkg==";
	private int maxPool   = 10;
    		
	private OnePassRelayManager mgr; 
	@Before
	public void init() {
		mgr = OnePassRelayManager.getInstace();
		mgr.initialize(serverUrl, serviceId, siteId, AppID,maxPool);
	}
	
	@Test
	public void regist() {
		System.out.println("regist");
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassResponse response = oprc.regist(user_id, device_id);
		System.out.println(response);
	}
	
	@Test
	public void release() {
		System.out.println("release");
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassResponse response = oprc.release(user_id, device_id);
		System.out.println(response);
	}
	
	@Test
	public void auth() {
		System.out.println("auth");
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassResponse response = oprc.auth(user_id, device_id);
		System.out.println(response);
	}
	
	@Test
	public void change() {
		System.out.println("auth");
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassResponse response = oprc.change(user_id, device_id);
		System.out.println(response);
	}
	
	@Test
	public void init_auth() {
		System.out.println("init_auth");
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassResponse response = oprc.initAuthnr(user_id, device_id);
		System.out.println(response);
	}

	@Test
	public void init_device() {
		System.out.println("init_device");
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassResponse response = oprc.initDevice(user_id, device_id);
		System.out.println(response);
	}
	
	@Test
	public void result() {
		System.out.println("result");
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassResponse response = oprc.confirmResult();
		System.out.println(response);
		
	}
	
	@Test
	public void allowlist() {
		System.out.println("allowlist");
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassResponse response = oprc.allowedAuthnr();
		System.out.println(response);
	}
}
