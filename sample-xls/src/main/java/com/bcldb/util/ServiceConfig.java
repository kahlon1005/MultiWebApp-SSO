package com.bcldb.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ServiceConfig {

	public static final String FILE_PATH = "/tmp/";
	
	private URL endPoint;	
	private List<String> viewNames;

	public ServiceConfig() {
		InputStream input = null;
		
		Properties prop = new Properties();
		try {
			input = new FileInputStream("src/main/resources/config.properties");
			prop.load(input);

			endPoint = new URL(prop.getProperty("wms_endpoint_url"));
			viewNames = Arrays.asList(prop.getProperty("view_name").split("\\s*,\\s*"));
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// get view names
	public List<String> getViewNames() {
		return viewNames;
	}
	
	//get endpoint
	public URL getEndPoint(){
		return endPoint;
	}

	public String search(String viewName) {
		String request = "	<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:wsc='wsclient.wms.tecsys.com'>"
				+ "<soapenv:Header/>"
				+ "	<soapenv:Body>"
				+ "		<wsc:search>"
				+ "			<arg0>"
				+ "				<userName>system</userName>"
				+ "				<criteria>"
				+ "					<" + viewName+ ">"
				+ "					</" + viewName+ ">"
				+ "				</criteria>"
				+ "			</arg0>"
				+ "		</wsc:search>"
				+ "	</soapenv:Body>"
				+ "</soapenv:Envelope>";

		return request;
	}

	public String createOrUpdate(String data) {
		String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsc=\"wsclient.wms.tecsys.com\">\n"
				+ "   <soapenv:Header/>\n" 
				+ "   <soapenv:Body>\n"
				+ "      <wsc:update>\n" 
				+ "         <arg0>\n"
				+ "            <userName>system</userName>\n"
				+ "            <transactions>\n"
				+ "               <action>createOrUpdate</action>\n"
				+ "               <data>\n" 
				+ 					 data 
				+ "               </data>\n"
				+ "            </transactions>\n" 
				+ "         </arg0>\n"
				+ "      </wsc:update>\n" 
				+ "   </soapenv:Body>\n"
				+ "</soapenv:Envelope>\n";
		return request;
	}

}
