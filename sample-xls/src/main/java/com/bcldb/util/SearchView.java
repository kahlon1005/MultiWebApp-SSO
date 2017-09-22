package com.bcldb.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SearchView {

	

	public static void main(String[] args) {
		
		ServiceConfig service = new ServiceConfig();
		
		InputStream input = null;

		try {
			Properties prop = new Properties();
			
			input = new FileInputStream("src/main/resources/config.properties");
			prop.load(input);
			
			List<String> views = Arrays.asList(prop.getProperty("view_name").split("\\s*,\\s*"));
			for (String viewName : views){
				
				SOAPConnectionFactory cf = SOAPConnectionFactory.newInstance();
				SOAPConnection con = cf.createConnection();

				MessageFactory mf = MessageFactory.newInstance();
				String result = service.search(viewName);
				SOAPMessage request = mf.createMessage(null, new ByteArrayInputStream(result.getBytes()));
				SOAPMessage response = con.call(request, service.getEndPoint());

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.writeTo(out);
				

				// read node elements
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new InputSource(new StringReader(out.toString())));
				doc.getDocumentElement().normalize();

				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile("//result/" + viewName);

				Object result1 = expr.evaluate(doc, XPathConstants.NODESET);
				NodeList nList = (NodeList) result1;

				boolean isHeaderExists = false;

				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet(viewName);

				//creating excel
				int rowNum = 0;

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						NodeList cList = eElement.getChildNodes();

						Row row = sheet.createRow(rowNum++);
						int colNum = 0;

						// header row
						if (!isHeaderExists) {

							for (int ctemp = 0; ctemp < cList.getLength(); ctemp++) {
								Node cNode = cList.item(ctemp);
								if (cNode.getNodeType() == Node.ELEMENT_NODE) {
									Cell cell = row.createCell(colNum++);
									if (cNode.getNodeName() instanceof String) {
										cell.setCellValue((String) cNode.getNodeName());
									}
								}
							}
							isHeaderExists = true;						
							row = sheet.createRow(rowNum++);
							colNum = 0;
						}

						// data
						for (int ctemp = 0; ctemp < cList.getLength(); ctemp++) {
							Node cNode = cList.item(ctemp);
							if (cNode.getNodeType() == Node.ELEMENT_NODE) {
								Cell cell = row.createCell(colNum++);
								if (cNode.getTextContent() instanceof String) {
									cell.setCellValue((String) cNode.getTextContent());
								}
							}
						}

					}
				}

				FileOutputStream outputStream = new FileOutputStream(ServiceConfig.FILE_PATH  + viewName + ".xlsx");

				workbook.write(outputStream);
				workbook.close();
				System.out.println(ServiceConfig.FILE_PATH  + viewName + ".xlsx created successfully ...");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
