package com.raonsecure.op.relay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
public class OnePassClient {
	private OnePassRelayManager mgr = OnePassRelayManager.getInstace();
	private final String svcTrId;
	
	public OnePassClient(String trid) {
		this.svcTrId = trid;
	}

	public OnePassResponse regist(OnePassRelayRequest relay_req) {
		OnePassRequest request = new OnePassRequest(relay_req);
		request.setCommand("requestServiceRegist");
		
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(this.svcTrId);
		
		return post(request);
	}
	
	public OnePassResponse release(OnePassRelayRequest relay_req) {
		OnePassRequest request = new OnePassRequest(relay_req);
		request.setCommand("requestServiceRelease");
		
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(this.svcTrId);
		
		return post(request);
	}
	
	public OnePassResponse auth(OnePassRelayRequest relay_req) {
		OnePassRequest request = new OnePassRequest(relay_req);
		request.setCommand("requestServiceAuth");
		
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(this.svcTrId);
		
		return post(request);
	}
	
	public OnePassResponse change(OnePassRelayRequest relay_req) {
		OnePassRequest request = new OnePassRequest(relay_req);
		request.setCommand("requestServiceChangeAuthnr");
		
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(this.svcTrId);
		
		return post(request);
	}
	
	public OnePassResponse initAuthnr(OnePassRelayRequest relay_req) {
		OnePassRequest request = new OnePassRequest(relay_req);
		request.setCommand("requestServiceInitAuthnr");
		
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(this.svcTrId);
		
		return post(request);
	}
	
	public OnePassResponse initDevice(OnePassRelayRequest relay_req) {
		OnePassRequest request = new OnePassRequest(relay_req);
		request.setCommand("requestServiceInitDevice");
		
		request.setBizReqType("server");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(this.svcTrId);
		
		return post(request);
	}
	
	
	public OnePassResponse confirm() {
		OnePassRequest request = new OnePassRequest();
		request.setCommand("trResultConfirm");
		request.setSvcTrId(this.svcTrId);
		
		return post(request);
	}
	
	
	public OnePassResponse allowed() {
		OnePassRequest request = new OnePassRequest();
		request.setCommand("allowedAuthnr");
		request.setSvcId(mgr.getServiceId());
		request.setSiteId(mgr.getSiteId());
		request.setSvcTrId(this.svcTrId);
		
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
	private void get() {
		
	}
}
