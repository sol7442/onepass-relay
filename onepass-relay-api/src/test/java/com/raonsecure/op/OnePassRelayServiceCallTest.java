package com.raonsecure.op;

import org.junit.Before;
import org.junit.Test;

import com.raonsecure.op.relay.OnePassRelayClient;
import com.raonsecure.op.relay.OnePassRelayManager;
import com.raonsecure.op.relay.OnePassRelayResponse;

public class OnePassRelayServiceCallTest {

	private String SiteID 	 = "TEST0000000000";
    private String ServiceID = "HC0000000000";
    private String AppID     = "android:apk-key-hash:Df+2X53Z0UscvUu6obxC3rIfFyk";
    
    private String user_id = "userId";
	private String device_id = "gYJKoZIhvcNAQcCoIIH7zCCB+sCAQExDzANBglghkg==";
	
    		
	private OnePassRelayManager mgr; 
	@Before
	public void init() {
		mgr = OnePassRelayManager.getInstace();
		mgr.initialize("http://localhost:8080/OnePassRelay-Server/OnePassRelayService", ServiceID, SiteID,AppID);
	}
	
	@Test
	public void regist() {
		OnePassRelayClient oprc = new OnePassRelayClient();
		OnePassRelayResponse response = oprc.regist(user_id, device_id);
		System.out.println(response);
	}
	
	@Test
	public void release() {
		System.out.println("release");

	}
	
	@Test
	public void auth() {
		System.out.println("auth");
	}
	
	@Test
	public void change() {
		System.out.println("auth");
	}
	
	@Test
	public void init_auth() {
		System.out.println("init_auth");
	}

	@Test
	public void init_device() {
		System.out.println("init_device");
	}
	
	@Test
	public void result() {
		System.out.println("result");
		
	}
	
	@Test
	public void allowlist() {
		System.out.println("allowlist");
		
	}
}
