<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="org.apache.http.util.EntityUtils"%>
<%@page import="org.apache.http.HttpEntity"%>
<%@page import="org.apache.http.client.methods.CloseableHttpResponse"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="org.apache.http.entity.StringEntity"%>
<%@page import="org.apache.http.client.methods.HttpPost"%>
<%@page import="org.apache.http.impl.client.HttpClients"%>
<%@page import="org.apache.http.impl.conn.PoolingHttpClientConnectionManager"%>
<%@page import="org.apache.http.impl.client.CloseableHttpClient"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%!
	private final int maxPoolSize = 1000;
	private final int maxRoute = 10;
	private final String server_url = "https://onepassdemo.raonsecure.co.kr:9300/interfBiz/processRequest.do";
	private CloseableHttpClient client;

	public void jspInit() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(maxPoolSize);		
		cm.setDefaultMaxPerRoute(maxRoute);
		this.client = HttpClients.custom().setConnectionManager(cm).build();
	}
	
	private String parse_request(HttpServletRequest request) throws IOException {
		StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
		return buffer.toString();
	}
	
	
%>

<%
	String req_json = parse_request(request);
	String res_json = "";		
System.out.println("req : " + req_json);
	HttpPost post = new HttpPost(server_url);
	post.addHeader("Content-Type", "application/json");
	post.setEntity(new StringEntity(req_json));
	HttpEntity entity = this.client.execute(post).getEntity();
	res_json = EntityUtils.toString(entity);

System.out.println("res : " + res_json);
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	
	//BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
	//writer.write(res_json);
	//writer.flush();
	out.write(res_json);
	out.flush();

%>