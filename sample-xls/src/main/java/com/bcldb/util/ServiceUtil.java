package com.bcldb.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ServiceUtil {

	/**
	 * transform xml output to file
	 * 
	 * @param xml
	 * @return
	 */
	public static void xmlToFile(String xml, String filename) throws Exception {
		
		// Instantiate transformer input
		Source xmlInput = new StreamSource(new StringReader(xml));
		StreamResult xmlOutput = new StreamResult(new File(filename));
		
		//System.out.println("Request : " + xml.toString());
		
		// Configure transformer
		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"testing.dtd");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		transformer.transform(xmlInput, xmlOutput);

	}
	
	
	/**
	 * service call 
	 * 
	 * @return response
	 * 
	 * @param url
	 * @param env
	 * @throws Exception 
	 */
	public static String call(String url, String env) throws Exception {

		URL endpoint = new URL(url);

		SOAPConnectionFactory cf = SOAPConnectionFactory.newInstance();
		SOAPConnection con = cf.createConnection();

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage request = mf.createMessage(null, new ByteArrayInputStream(env.getBytes()));

		SOAPMessage response = con.call(request, endpoint);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.writeTo(out);

		return out.toString();

	}
}
