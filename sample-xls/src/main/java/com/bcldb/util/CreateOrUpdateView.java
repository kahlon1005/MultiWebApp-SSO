package com.bcldb.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class CreateOrUpdateView {

	public static void main(String[] args) {
		ServiceConfig service = new ServiceConfig();
		
		try {
			List<String> views = service.getViewNames();
			
			for (String viewName : views){
				
			    System.out.println("processing view : " + viewName);
			    
			    FileInputStream excelFile = new FileInputStream(new File(ServiceConfig.FILE_PATH + viewName + ".xlsx"));
				Workbook workbook = new XSSFWorkbook(excelFile);
				Sheet datatypeSheet = workbook.getSheetAt(0);
				
				Iterator<Row> iterator = datatypeSheet.iterator();
				
				Map<Integer, String> map = new HashMap<Integer, String>();
				
				StringBuffer sb = new StringBuffer();
				
				while (iterator.hasNext()) {
					StringBuffer sb1 = new StringBuffer();
					
					Row currentRow = iterator.next();
					Iterator<Cell> cellIterator = currentRow.iterator();
					
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						if (currentRow.getRowNum() == 0) {
							map.put(Integer.valueOf(currentCell.getColumnIndex()), currentCell.getStringCellValue());
						} else {						
							sb1.append("<" +map.get(Integer.valueOf(currentCell.getColumnIndex())) +">");
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								sb1.append(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								//handle integer value
								double fractionalPart = currentCell.getNumericCellValue() % 1;
								if(fractionalPart == 0.0){
									sb1.append((long)currentCell.getNumericCellValue());
								} else{
									sb1.append(currentCell.getNumericCellValue());
								}
							}
							sb1.append("</" +map.get(Integer.valueOf(currentCell.getColumnIndex())) +">\n");
						}
					}
					//wrap within view
					if (currentRow.getRowNum() > 0) {
						sb.append("<" + datatypeSheet.getSheetName() + ">\n");
						sb.append(sb1);
						sb.append("</" + datatypeSheet.getSheetName() + ">\n");
					}
				}
				
				
				
				SOAPConnectionFactory cf = SOAPConnectionFactory.newInstance();
				SOAPConnection con = cf.createConnection();

				MessageFactory mf = MessageFactory.newInstance();
				String result = service.createOrUpdate(sb.toString());
				SOAPMessage request = mf.createMessage(null, new ByteArrayInputStream(result.getBytes()));
				
				SOAPMessage response = con.call(request, service.getEndPoint());

				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.writeTo(out);
				
				//response.writeTo(System.out);
				//return out.toString();
				
				
				// read errors 
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new InputSource(new StringReader(out.toString())));
				doc.getDocumentElement().normalize();
				
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile("//data/"+viewName+"/Errors");
				
				Object result1 = expr.evaluate(doc, XPathConstants.NODESET);
				NodeList nList = (NodeList) result1;
				if(nList.getLength() > 0){
					System.out.println(viewName + " contains errors ...");
				} else{
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							NodeList cList = eElement.getChildNodes();
							for (int ctemp = 0; ctemp < cList.getLength(); ctemp++) {
								Node cNode = cList.item(ctemp);
								if (cNode.getNodeType() == Node.ELEMENT_NODE) {
									if (cNode.getTextContent() instanceof String) {
										System.out.println(cNode.getTextContent());
									}
								}
							}
						}
					}
					System.out.println(viewName + " successfully updated ...");
				}
			}
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
