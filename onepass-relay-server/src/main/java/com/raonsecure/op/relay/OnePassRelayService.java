package com.raonsecure.op.relay;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OnePassRelayService
 */
public class OnePassRelayService extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	private static final String SER_REGIST = "REGIST";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnePassRelayService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		System.out.println("pathInfo : " + pathInfo);
		
		
		OnePassRelayResponse relay_res = null;
		OnePassRelayRequest  relay_req = null;
		
		relay_req = parse_relay_request(request);
		
		if(pathInfo.equals("SER_REGIST")) {
			relay_res = regist(relay_req);
		}else if(pathInfo.equals("")) {
			
		}
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private OnePassRelayResponse regist(OnePassRelayRequest relay_req) {
		System.out.println("relay_req : " + relay_req.toJsonString());
		
		return null;
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		System.out.println("pathInfo : " + pathInfo);
				
		doGet(request, response);
	}

}
