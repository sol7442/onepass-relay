package com.raonsecure.op.relay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
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
	public OnePassRelayResponse regist(String userId,String deviceId) {
		OnePassRelayRequest request = new OnePassRelayRequest();
		request.setAppId(mgr.getAppId());
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(OnePassServicePath.SRV_REGIST,request);
	}
	
	/**
	 * OnePass를 통한 간편인증 서비스를 해지하기 위해 OnePass Server에 등록된 인증장치에 대해 삭제를 요청합니다.
	 */
	public OnePassRelayResponse release(String userId,String deviceId) {
		OnePassRelayRequest request = new OnePassRelayRequest();
		request.setAppId(mgr.getAppId());
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(OnePassServicePath.SRV_RELEASE,request);
	}
	
	/**
	 * OnePass를 통한 간편인증을 요청합니다.
	 */
	public OnePassRelayResponse auth(String userId,String deviceId) {
		OnePassRelayRequest request = new OnePassRelayRequest();
		request.setAppId(mgr.getAppId());
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(OnePassServicePath.SRV_AUTH,request);
	}
	
	/**
	 * OnePass Server에 등록했던 인증장치를 다른 인증장치로 변경등록을 요청합니다. 
	 */
	public OnePassRelayResponse change(String userId,String deviceId) {
		OnePassRelayRequest request = new OnePassRelayRequest();
		request.setAppId(mgr.getAppId());
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(OnePassServicePath.SRV_CHANGE,request);
	}
	
	/**
	 * 단말 초기화 등으로 인증장치를 사용할 수 없게 된 경우에, OnePass Server에 등록된 인증장치 정보를 모두 초기화 하고 새로운 인증장치를 등록 요청합니다. 
	 */
	public OnePassRelayResponse initAuthnr(String userId,String deviceId) {
		OnePassRelayRequest request = new OnePassRelayRequest();
		request.setAppId(mgr.getAppId());
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(OnePassServicePath.SRV_INIT_AUTHNR,request);
	}
	
	/**
	 * 타인에 의해 OnePass Server에 등록하여 사용하던 단말기의 정보를 초기화 하고, 새로 요청하는 사용자가 향후 간편인증에 사용할 수 있도록 원패스 시스템에 등록을 요청합니다.
	 */
	public OnePassRelayResponse initDevice(String userId,String deviceId) {
		OnePassRelayRequest request = new OnePassRelayRequest();
		request.setAppId(mgr.getAppId());
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post(OnePassServicePath.SRV_INIT_DEVICE,request);
	}
	
	
	/**
	 * 업무서버에서 요청한 서비스에 대하여 거래 상태를 확인하기 위하여 거래결과 확인을 요청합니다.
	 */
	public OnePassRelayResponse confirmResult() {
		return get(OnePassServicePath.SRV_CONFIRM);
	}
	
	
	/**
	 * 업무서버에서 요청한 사이트/서비스에 대한 등록허용된 인증장치 목록을 제공한다. 
	 */
	public OnePassRelayResponse allowedAuthnr() {
		return get(OnePassServicePath.SRV_ALLOWED);
	}
	
	private OnePassRelayResponse post(String path, OnePassRelayRequest request) {
		CloseableHttpResponse http_res = null;
		OnePassRelayResponse  response = null;
		try {
			mgr.getClient();
			HttpPost post = new HttpPost(mgr.getServerUrl() + path);
			post.addHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(request.toJsonString()));
			
			http_res = mgr.getClient().execute(post);
			HttpEntity entity = http_res.getEntity();
			
			OnePassResponse relay_res = OnePassResponse.fromJson(EntityUtils.toString(entity));
			response = new OnePassRelayResponse(relay_res);
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
	private OnePassRelayResponse get(String path) {
		CloseableHttpResponse http_res = null;
		OnePassRelayResponse  response = null;
		try {
			mgr.getClient();
			HttpGet get = new HttpGet(mgr.getServerUrl() + path);
			get.addHeader("Content-Type", "application/json");
			
			http_res = mgr.getClient().execute(get);
			HttpEntity entity = http_res.getEntity();
			
			OnePassResponse relay_res = OnePassResponse.fromJson(EntityUtils.toString(entity));
			response = new OnePassRelayResponse(relay_res);
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
}
