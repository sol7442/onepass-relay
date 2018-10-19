package com.raonsecure.op.relay;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;import java.nio.charset.Charset;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.net.ssl.ConfigurableSSLServerSocketFactory;


/**
 * Servlet implementation class OnePassRelayService
 */
public class OnePassRelayService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(OnePassRelayService.class);
	
			
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
		
		mgr = OnePassRelayManager.getInstace();
		mgr.initialize(serverUrl, serviceId, siteId, Integer.parseInt(maxPool));

		String logger_type     = config.getInitParameter("LoggerType");;
		String logger_level    = config.getInitParameter("LoggerLevel");;
		String logger_filepath = config.getInitParameter("LoggerPath");;
		String logger_filename = config.getInitParameter("LoggerFile");;
		String loffer_config   = config.getInitParameter("LoggerConfig");;
		
		System.setProperty("logger.type",logger_type);
		System.setProperty("logger.level",logger_level);
		System.setProperty("logger.filepath",logger_filepath);
		System.setProperty("logger.filename",logger_filename);
		//"D:/workspace/git/onepass-relay/onepass-relay-server/src/main/resources/logback.xml"
	    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
	    JoranConfigurator configurator = new JoranConfigurator();
	    configurator.setContext(context);
	    context.reset();
	    try {
			configurator.doConfigure(new File(loffer_config));
		} catch (JoranException e) {
			e.printStackTrace();
		}
	    
//	    context.reset();
//	    context.do
//	    context.reconfigure();
//	    context.updateLoggers();

	    
	    System.out.println(ContextInitializer.CONFIG_FILE_PROPERTY);
		
		logger.info("OnePassRelayManager {}: ",mgr);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		OnePassRequest onepass_req = new OnePassRequest();
		OnePassResponse onepass_res = null;
		OnePassRelayResponse  relay_res = null;

		if(pathInfo.equals(OnePassServicePath.SRV_CONFIRM)) {
			onepass_res = new OnePassClient(makeTrId()).confirm(onepass_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_ALLOWED)) {
			onepass_res = new OnePassClient(makeTrId()).allowed(onepass_req);
		}
		
		
		relay_res = new OnePassRelayResponse(onepass_res);
		logger.info("RelayRequest  : {},{}: ",pathInfo,onepass_req);
		logger.info("RelayResponse : {},{},{}: ",pathInfo,onepass_res,relay_res);
		
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
		OnePassRequest  onepass_req = null;
		OnePassResponse onepass_res = null;
		OnePassRelayRequest   relay_req = null;
		OnePassRelayResponse  relay_res = null;
		
		relay_req   = parse_relay_request(request);
		onepass_req = new OnePassRequest(relay_req);
		
		if(pathInfo.equals(OnePassServicePath.SRV_REGIST)) {
			onepass_res = new OnePassClient(makeTrId()).regist(onepass_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_RELEASE)) {
			onepass_res = new OnePassClient(makeTrId()).release(onepass_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_AUTH)) {
			onepass_res = new OnePassClient(makeTrId()).auth(onepass_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_CHANGE)) {
			onepass_res = new OnePassClient(makeTrId()).change(onepass_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_INIT_AUTHNR)) {
			onepass_res = new OnePassClient(makeTrId()).initAuthnr(onepass_req);
		}else if(pathInfo.equals(OnePassServicePath.SRV_INIT_DEVICE)) {
			onepass_res = new OnePassClient(makeTrId()).initDevice(onepass_req);
		}
		
		relay_res = new OnePassRelayResponse(onepass_res);
		logger.info("RelayRequest  : {},{},{}: ",pathInfo,relay_req,onepass_req);
		logger.info("RelayResponse : {},{},{}: ",pathInfo,onepass_res,relay_res);
		
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
