package com.raonsecure.op.relay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

/**
 * @author Yong
 *
 */
public class OnePassRelayClient {
	private OnePassRelayManager mgr = OnePassRelayManager.getInstace();
	
	/**
	 * OnePass를 통한 간편인증 서비스를 이용할 수 있도록 사용자의 인증장치를 OnePass Server에 등록 요청합니다.
	 */
	public OnePassResponse regist(String userId,String deviceId) {
		OnePassRequest request = new OnePassRequest();

		request.setCommand("requestServiceRegist");
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(makeTrId());
		request.setAppId(mgr.getAppId());
		
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(request);
	}
	
	/**
	 * OnePass를 통한 간편인증 서비스를 해지하기 위해 OnePass Server에 등록된 인증장치에 대해 삭제를 요청합니다.
	 */
	public OnePassResponse release(String userId,String deviceId) {
		OnePassRequest request = new OnePassRequest();

		request.setCommand("requestServiceRelease");
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(makeTrId());
		request.setAppId(mgr.getAppId());
		
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(request);
	}
	
	/**
	 * OnePass를 통한 간편인증을 요청합니다.
	 */
	public OnePassResponse auth(String userId,String deviceId) {
		OnePassRequest request = new OnePassRequest();

		request.setCommand("requestServiceAuth");
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(makeTrId());
		request.setAppId(mgr.getAppId());
		
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(request);
	}
	
	/**
	 * OnePass Server에 등록했던 인증장치를 다른 인증장치로 변경등록을 요청합니다. 
	 */
	public OnePassResponse change(String userId,String deviceId) {
		OnePassRequest request = new OnePassRequest();

		request.setCommand("requestServiceChangeAuthnr");
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(makeTrId());
		request.setAppId(mgr.getAppId());
		
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(request);
	}
	
	/**
	 * 단말 초기화 등으로 인증장치를 사용할 수 없게 된 경우에, OnePass Server에 등록된 인증장치 정보를 모두 초기화 하고 새로운 인증장치를 등록 요청합니다. 
	 */
	public OnePassResponse initAuthnr(String userId,String deviceId) {
		OnePassRequest request = new OnePassRequest();

		request.setCommand("requestServiceInitAuthnr");
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(makeTrId());
		request.setAppId(mgr.getAppId());
		
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(request);
	}
	
	/**
	 * 타인에 의해 OnePass Server에 등록하여 사용하던 단말기의 정보를 초기화 하고, 새로 요청하는 사용자가 향후 간편인증에 사용할 수 있도록 원패스 시스템에 등록을 요청합니다.
	 */
	public OnePassResponse initDevice(String userId,String deviceId) {
		OnePassRequest request = new OnePassRequest();

		request.setCommand("requestServiceInitDevice");
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(makeTrId());
		request.setAppId(mgr.getAppId());
		
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(request);
	}
	
	
	/**
	 * 업무서버에서 요청한 서비스에 대하여 거래 상태를 확인하기 위하여 거래결과 확인을 요청합니다.
	 */
	public OnePassResponse confirmResult() {
		OnePassRequest request = new OnePassRequest();

		request.setCommand("trResultConfirm");		
		request.setSvcTrId(makeTrId());
		
		return post(request);
	}
	
	
	/**
	 * 업무서버에서 요청한 사이트/서비스에 대한 등록허용된 인증장치 목록을 제공한다. 
	 */
	public OnePassResponse allowedAuthnr() {
		OnePassRequest request = new OnePassRequest();

		request.setCommand("allowedAuthnr");
		
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(makeTrId());
		
		
		return post(request);
	}
	
	private OnePassResponse post( OnePassRequest request) {
		CloseableHttpResponse http_res = null;
		OnePassResponse  response = null;
		try {
			
			mgr.getClient();
			HttpPost post = new HttpPost(mgr.getServerUrl());
			post.addHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(request.toJsonString()));
			http_res = mgr.getClient().execute(post);
			HttpEntity entity = http_res.getEntity();
			
			response = OnePassResponse.fromJson(EntityUtils.toString(entity));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				http_res.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return response;
	}
//	
//	private OnePassRelayResponse get(String path) {
//		CloseableHttpResponse http_res = null;
//		OnePassRelayResponse  response = null;
//		try {
//			mgr.getClient();
//			HttpGet get = new HttpGet(mgr.getServerUrl() + path);
//			get.addHeader("Content-Type", "application/json");
//			
//			http_res = mgr.getClient().execute(get);
//			HttpEntity entity = http_res.getEntity();
//			
//			OnePassResponse relay_res = OnePassResponse.fromJson(EntityUtils.toString(entity));
//			response = new OnePassRelayResponse(relay_res);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				http_res.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return response;
//	}
	
	private String makeTrId() {
		byte[] buffer = new byte[10];
		Random rand = new Random();
		rand.nextBytes(buffer);
		return byteArrayToHex(buffer);
	}
	
	private String byteArrayToHex(byte[] a) {
	    StringBuilder sb = new StringBuilder();
	    for(final byte b: a)
	        sb.append(String.format("%02x", b&0xff));
	    
	    return sb.toString();
	}
}
