package com.raonsecure.op.relay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author Yong
 *
 */
public class OnePassRelayClient {
	private OnePassRelayManager mgr = OnePassRelayManager.getInstace();
	
	/**
	 * OnePass�� ���� �������� ���񽺸� �̿��� �� �ֵ��� ������� ������ġ�� OnePass Server�� ��� ��û�մϴ�.
	 */
	public OnePassRelayResponse regist(String userId,String deviceId) {
		OnePassRelayRequest request = new OnePassRelayRequest();
		request.setLoginId(userId);
		request.setDeviceId(deviceId);
		
		return post("Regist",request);
	}
	
	/**
	 * OnePass�� ���� �������� ���񽺸� �����ϱ� ���� OnePass Server�� ��ϵ� ������ġ�� ���� ������ ��û�մϴ�.
	 */
	public void release() {}
	
	/**
	 * OnePass�� ���� ���������� ��û�մϴ�.
	 */
	public void auth() {}
	
	/**
	 * OnePass Server�� ����ߴ� ������ġ�� �ٸ� ������ġ�� �������� ��û�մϴ�. 
	 */
	public void changeAuthnr() {}
	
	/**
	 * �ܸ� �ʱ�ȭ ������ ������ġ�� ����� �� ���� �� ��쿡, OnePass Server�� ��ϵ� ������ġ ������ ��� �ʱ�ȭ �ϰ� ���ο� ������ġ�� ��� ��û�մϴ�. 
	 */
	public void initAuthnr() {}
	
	/**
	 * Ÿ�ο� ���� OnePass Server�� ����Ͽ� ����ϴ� �ܸ����� ������ �ʱ�ȭ �ϰ�, ���� ��û�ϴ� ����ڰ� ���� ���������� ����� �� �ֵ��� ���н� �ý��ۿ� ����� ��û�մϴ�.
	 */
	public void initDevice() {}
	
	
	/**
	 * ������������ ��û�� ���񽺿� ���Ͽ� �ŷ� ���¸� Ȯ���ϱ� ���Ͽ� �ŷ���� Ȯ���� ��û�մϴ�.
	 */
	public void confirmResult() {
		
	}
	
	
	/**
	 * ������������ ��û�� ����Ʈ/���񽺿� ���� ������� ������ġ ����� �����Ѵ�. 
	 */
	public void allowedAuthnr() {
		
	}
	
	private OnePassRelayResponse post(String path, OnePassRelayRequest request) {
		CloseableHttpResponse http_res = null;
		OnePassRelayResponse  response = null;
		try {
			mgr.getClient();
			HttpPost post = new HttpPost(mgr.getRelayServerUrl() + "/" + path);
			post.addHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(request.toJsonString()));
			
			http_res = mgr.getClient().execute(post);
			HttpEntity entity = http_res.getEntity();
			System.out.println(entity);
			
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
	private void get() {
		
	}
}
