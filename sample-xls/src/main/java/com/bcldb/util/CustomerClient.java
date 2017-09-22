package com.bcldb.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bcldb.util.ServiceUtil;

public class CustomerClient {

	private static final String SERVICE_END_POINT = "http://tswmsea.sbox.bcldb.com/sbx_94x/ws/WmsWebService";

	private static final String ROOT_DIR = "C:\\dev\\soapui-test";

	private static final String DATE_FORMAT = "YYYY-MM-dd_hh-mm-sss";

	private static final String SEPRATOR = "\\";

	private static final String FILE_NAME = "tecsys_customer_reponse";
	private static final String FILE_EXTENSION = "xml";

	static Format formatter = new SimpleDateFormat(DATE_FORMAT);

	public static void main(String[] args) {

		createCustomer();

		System.out.println("process end .................");
	}

	private static void createCustomer() {
		System.out.println("create customer ...");
		String request = getCustomer("WmsCustomer");
		String filename = ROOT_DIR + SEPRATOR + FILE_NAME + "_"
				+ formatter.format(new Date()) + "." + FILE_EXTENSION;
		parseXml(request, filename);

		System.out.println(" Results loaded to file ................  "
				+ filename);

	}

	/**
	 * parse xml
	 * 
	 */
	static void parseXml(String request, String filename) {
		try {
			String xml = ServiceUtil.call(SERVICE_END_POINT, request);
			ServiceUtil.xmlToFile(xml, filename);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	static String getCustomer(String viewName) {
		String reqEnv = "	<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:wsc='wsclient.wms.tecsys.com'>"
				+ "<soapenv:Header/>"
				+ "	<soapenv:Body>"
				+ "		<wsc:search>"
				+ "			<arg0>"
				+ "				<userName>system</userName>"
				+ "				<criteria>"
				+ "					<"+viewName+">"
				+ "					</"+viewName+">"
				+ "				</criteria>"
				+ "			</arg0>"
				+ "		</wsc:search>"
				+ "	</soapenv:Body>"
				+ "</soapenv:Envelope>";

		System.out.println("Request " + reqEnv);
		return reqEnv;
	}

}
