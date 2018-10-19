package com.raonsecure.op.relay;

import java.lang.reflect.Modifier;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OnePassRelayManager {
	
	private transient static OnePassRelayManager instance;
	private transient CloseableHttpClient client;
	
	private String serverUrl;
	private String serviceId;
	private String siteId;
	private String appId;
	private int maxPoolSize;
	
	public int getMaxPoolSize() {
		return maxPoolSize;
	}
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	
	
	private OnePassRelayManager() {}
	public static OnePassRelayManager getInstace() {
		if(instance == null) {
			instance = new OnePassRelayManager();
		}
		return instance;
	}
	public void initialize(String url, String srvId, String siteId, int maxPool) {
		this.serverUrl = url;
		this.serviceId = srvId;
		this.siteId = siteId;
		this.maxPoolSize = maxPool;
		
		if(this.client == null) {
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(this.maxPoolSize);		
			cm.setDefaultMaxPerRoute(10);
			this.client = HttpClients.custom().setConnectionManager(cm).build();
		}
	}
	
	public void initialize(String url, String appId, int maxPool) {
		this.serverUrl = url;
		this.appId  = appId;
		this.maxPoolSize = maxPool;
		
		if(this.client == null) {
			System.out.println("connectino pool init");
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(this.maxPoolSize);		
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
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
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
	
	public String toString() {
	    GsonBuilder builder = new GsonBuilder();
	    builder.excludeFieldsWithModifiers(Modifier.TRANSIENT);  
	    builder.setPrettyPrinting();
		Gson gson = builder.create();
		return gson.toJson(this);
	}
}
