package com.raonsecure.op.relay;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class OnePassRelayManager {
	private static OnePassRelayManager instance;
	private String relayServerUrl;
	private String serviceId;
	private String siteId;
	private String appId;
	
	private CloseableHttpClient client;
	
	private OnePassRelayManager() {}
	public static OnePassRelayManager getInstace() {
		if(instance == null) {
			instance = new OnePassRelayManager();
		}
		return instance;
	}
	public void initialize(String url, String srvId, String siteId, String appId) {
		this.relayServerUrl = url;
		this.serviceId = srvId;
		this.siteId = siteId;
		this.appId  = appId;
		
		if(this.client == null) {
			System.out.println("connectino pool init");
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(100);		
			cm.setDefaultMaxPerRoute(10);
			this.client = HttpClients.custom().setConnectionManager(cm).build();
		}
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getRelayServerUrl() {
		return relayServerUrl;
	}
	public void setRelayServerUrl(String relayServerUrl) {
		this.relayServerUrl = relayServerUrl;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
	public CloseableHttpClient getClient() {
		return this.client;
	}
}
