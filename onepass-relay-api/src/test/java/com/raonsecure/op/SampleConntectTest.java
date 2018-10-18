package com.raonsecure.op;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
//
//import com.raon.op.relay.AbstractOenPassRequest;
//import com.raon.op.relay.ServiceAllowedAuthnrRequest;
//import com.raon.op.relay.ServiceAuthRequest;
//import com.raon.op.relay.ServiceChangeAuthnrRequest;
//import com.raon.op.relay.ServiceInitAuthnrRequest;
//import com.raon.op.relay.ServiceInitDeviceRequest;
//import com.raon.op.relay.ServiceRegistRequest;
//import com.raon.op.relay.ServiceReleaseRequest;
//import com.raon.op.relay.ServiceResultConfirmRequest;

public class SampleConntectTest {

	private CloseableHttpClient client;
    private String SiteID 	 = "TEST0000000000";
    private String ServiceID = "HC0000000000";

	@Before
	public void http_init() {
		System.out.println("before --");
		
		if(this.client == null) {
			init_pool();
		}
	}
	
	private void init_pool() {
		System.out.println("init - cm");
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);		
		cm.setDefaultMaxPerRoute(10);
		
		this.client = HttpClients.custom().setConnectionManager(cm).build();
	}

//	@Test
//	public void get() {
//		System.out.println("get--");
//		
//		ServiceRegistRequest request = gen_reg_req();
//		
//	}
//	
//	@Test
//	public void regist() {
//		System.out.println("regist");
//		String url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
//		ServiceRegistRequest request = gen_reg_req();
//		post(url,request);
//	}
//	
//	@Test
//	public void release() {
//		System.out.println("release");
//		String url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
//		OenPassRequest request = gen_rel_req();
//		post(url,request);
//	}
//	
//	@Test
//	public void auth() {
//		System.out.println("auth");
//		String url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
//		OenPassRequest request = gen_auth_req();
//		post(url,request);
//	}
//	
//	@Test
//	public void change() {
//		System.out.println("auth");
//		String url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
//		OenPassRequest request = gen_change_req();
//		post(url,request);
//	}
//	
//	@Test
//	public void init_auth() {
//		System.out.println("init_auth");
//		String url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
//		OenPassRequest request = gen_initauth_req();
//		post(url,request);
//	}
//
//	@Test
//	public void init_device() {
//		System.out.println("init_device");
//		String url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
//		OenPassRequest request = gen_initdevice_req();
//		post(url,request);
//	}
//	
//	@Test
//	public void result() {
//		System.out.println("result");
//		String url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
//		OenPassRequest request = gen_result_req();
//		post(url,request);
//	}
//	
//	@Test
//	public void allowlist() {
//		System.out.println("allowlist");
//		String url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
//		OenPassRequest request = gen_allow_req();
//		post(url,request);
//	}
//	
//	private OenPassRequest gen_allow_req() {
//		ServiceAllowedAuthnrRequest request = new ServiceAllowedAuthnrRequest();
//		request.setSvcId(this.ServiceID);
//		request.setSiteId(this.SiteID);
//		
//		return request;
//	}
//
//	private OenPassRequest gen_result_req() {
//		ServiceResultConfirmRequest request = new ServiceResultConfirmRequest();
//		request.setSvcTrId(createTid());
//		return request;
//	}
//
//	private OenPassRequest gen_initdevice_req() {
//		ServiceInitDeviceRequest request = new ServiceInitDeviceRequest();
//		request.setSvcId(this.ServiceID);
//		request.setSiteId(this.SiteID);
//		request.setSvcTrId(createTid());
//
//		request.setLoginId("userId");
//		request.setDeviceId("gYJKoZIhvcNAQcCoIIH7zCCB+sCAQExDzANBglghkg==");
//		request.setAppId("android:apk-key-hash:Df+2X53Z0UscvUu6obxC3rIfFyk");
//		return request;
//
//	}
//
//	private OenPassRequest gen_initauth_req() {
//		ServiceInitAuthnrRequest request = new ServiceInitAuthnrRequest();
//		request.setSvcId(this.ServiceID);
//		request.setSiteId(this.SiteID);
//		request.setSvcTrId(createTid());
//
//		request.setLoginId("userId");
//		request.setDeviceId("gYJKoZIhvcNAQcCoIIH7zCCB+sCAQExDzANBglghkg==");
//		request.setAppId("android:apk-key-hash:Df+2X53Z0UscvUu6obxC3rIfFyk");
//		return request;
//	}
//
//	private OenPassRequest gen_change_req() {
//		ServiceChangeAuthnrRequest request = new ServiceChangeAuthnrRequest();
//		request.setSvcId(this.ServiceID);
//		request.setSiteId(this.SiteID);
//		request.setSvcTrId(createTid());
//
//		request.setLoginId("userId");
//		request.setDeviceId("gYJKoZIhvcNAQcCoIIH7zCCB+sCAQExDzANBglghkg==");
//		request.setAppId("android:apk-key-hash:Df+2X53Z0UscvUu6obxC3rIfFyk");
//		return request;
//	}
//
//	private OenPassRequest gen_auth_req() {
//		ServiceAuthRequest request = new ServiceAuthRequest();
//		request.setSvcId(this.ServiceID);
//		request.setSiteId(this.SiteID);
//		request.setSvcTrId(createTid());
//
//		request.setLoginId("userId");
//		request.setDeviceId("gYJKoZIhvcNAQcCoIIH7zCCB+sCAQExDzANBglghkg==");
//		request.setAppId("android:apk-key-hash:Df+2X53Z0UscvUu6obxC3rIfFyk");
//		return request;
//	}
//
//	
//	private ServiceRegistRequest gen_reg_req() {
//		ServiceRegistRequest request = new ServiceRegistRequest();
//		request.setSvcId(this.ServiceID);
//		request.setSiteId(this.SiteID);
//		request.setSvcTrId(createTid());
//
//		request.setLoginId("userId");
//		request.setDeviceId("gYJKoZIhvcNAQcCoIIH7zCCB+sCAQExDzANBglghkg==");
//		request.setAppId("android:apk-key-hash:Df+2X53Z0UscvUu6obxC3rIfFyk");
//		return request;
//	}
//	private ServiceReleaseRequest gen_rel_req() {
//		ServiceReleaseRequest request = new ServiceReleaseRequest();
//		request.setSvcId(this.ServiceID);
//		request.setSiteId(this.SiteID);
//		request.setSvcTrId(createTid());
//
//		request.setLoginId("userId");
//		request.setDeviceId("gYJKoZIhvcNAQcCoIIH7zCCB+sCAQExDzANBglghkg==");
//		request.setAppId("android:apk-key-hash:Df+2X53Z0UscvUu6obxC3rIfFyk");
//		return request;
//	}
//
//	
//	public void post(String url, OenPassRequest request) {
//		System.out.println(request.toJsonString());
//		HttpPost post = new HttpPost(url);
//		post.addHeader("Content-Type", "application/json");
//		try {
//			StringEntity req_entity = new StringEntity(request.toJsonString());
//			post.setEntity(req_entity);
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
//		
//		CloseableHttpResponse response = null;
//		try {
//			response = client.execute(post);
//			HttpEntity entity = response.getEntity();
//			System.out.println(EntityUtils.toString(entity));
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				response.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//	private String createTid() {
//		byte[] buffer = new byte[10];
//		Random rand = new Random();
//		rand.nextBytes(buffer);
//		return b2h(buffer);
//	}
//	private String b2h(byte[] bytes) {
//		StringBuilder sb = new StringBuilder(); 
//		for(byte b : bytes){ 
//			sb.append(String.format("%02X", b&0xff)); 
//		} 
//		return sb.toString(); 
//	}
}
