package com.raonsecure.op.relay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;import java.nio.charset.Charset;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class OnePassRelayService
 */
public class OnePassRelayService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OnePassRelayManager mgr; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnePassRelayService() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String serverUrl = config.getInitParameter("serverUrl");
		String serviceId = config.getInitParameter("serviceId");
		String siteId    = config.getInitParameter("siteId");
		String maxPool   = config.getInitParameter("maxPool");
		config.getInitParameter("");
		
		mgr = OnePassRelayManager.getInstace();
		mgr.initialize(serverUrl, serviceId, siteId, Integer.parseInt(maxPool));
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		OnePassResponse relay_res = null;
		
		if(pathInfo.equals(OnePassServicePath.SRV_CONFIRM)) {
			relay_res = new OnePassClient(makeTrId()).confirm();
		}else if(pathInfo.equals(OnePassServicePath.SRV_ALLOWED)) {
			relay_res = new OnePassClient(makeTrId()).allowed();
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		OutputStream out = response.getOutputStream();
		out.write(relay_res.toJsonString().getBytes("UTF-8"));
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		OnePassResponse relay_res = null;
		OnePassRelayRequest  relay_req = null;
		
		relay_req = parse_relay_request(request);
		if(pathInfo.equals(OnePassServicePath.SRV_REGIST)) {
			relay_res = new OnePassClient(makeTrId()).regist(relay_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_RELEASE)) {
			relay_res = new OnePassClient(makeTrId()).release(relay_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_AUTH)) {
			relay_res = new OnePassClient(makeTrId()).auth(relay_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_CHANGE)) {
			relay_res = new OnePassClient(makeTrId()).change(relay_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_INIT_AUTHNR)) {
			relay_res = new OnePassClient(makeTrId()).initAuthnr(relay_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_INIT_DEVICE)) {
			relay_res = new OnePassClient(makeTrId()).initDevice(relay_req);
		}
//		else if(pathInfo.equals(SRV_CONFIRM)) {
//			relay_res = new OnePassClient(makeTrId()).confirm(relay_req);
//		}else if(pathInfo.equals(SRV_ALLOWED)) {
//			relay_res = new OnePassClient(makeTrId()).allowed(relay_req);
//		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		OutputStream out = response.getOutputStream();
		out.write(relay_res.toJsonString().getBytes("UTF-8"));
		out.flush();
	}
	
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

	private OnePassRelayRequest parse_relay_request(HttpServletRequest request) throws IOException {
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
	    String payload = buffer.toString();
	    
		return OnePassRelayRequest.fromJson(payload);
	}



}
