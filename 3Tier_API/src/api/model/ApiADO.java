package api.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import api.API3Tier;

public class ApiADO extends API3Tier{
	
	private StringReader sr;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSr(StringReader sr) {
		this.sr = sr;
	}

	public ApiADO(StringReader stringReader, HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		setSr(stringReader);
		setRequest(request);
		setResponse(response);
	}
	
	@Override
	public void getRequest() {  //이건 추상이 맞다.
		
		DataSource dataFactory = dbConn();  //이런식으로 디비 연결?
		
		HashMap<String, Object> htData = new HashMap<String, Object>();
		InputSource is = new InputSource(sr);
	
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document doc = docBuilder.parse(is);
			doc.getDocumentElement().normalize();  //xml정규화
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			Element root = doc.getDocumentElement();

			String dataType = checkValidParam("dataType", root, doc);
			String count = checkValidParam("count", root, doc);
			String schemaCode = checkValidParam("schemaCode", root, doc);
			
			int cnt = Integer.parseInt(count);
			
			String tagName = "";
			Node nodeData = null;
			NodeList nodeList = null;
			Node node = null;
			
			String strNodeName = "";
			String strNodeValue = "";
			String strMethodName = "";
			
			htData.put("dataType", dataType);
			htData.put("count", count);
			htData.put("schemaCode", schemaCode);
			
			for (int i = 0; i < cnt; i++) {
				nodeData = doc.getElementsByTagName("data_" + (i+1)).item(0);
				nodeList = nodeData.getChildNodes();
				
				HashMap<String, String> htMethod  = new HashMap<String, String>();
				
				for (int j = 0; j < nodeList.getLength(); j++) {
					node = nodeList.item(j);
					
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						
						strNodeName = node.getNodeName();
						strNodeName = strNodeName.toUpperCase();
						
						strNodeValue = node.getTextContent();
						htMethod.put(strNodeName, strNodeValue);
						
						if (strNodeName.equals("METHODNAME")) {
							strMethodName = strNodeValue;
						}
					}
				}
				
				request.setAttribute("htMethod_" + (i+1), htMethod);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		
	}

	@Override
	public void inputDataParse() {  //이건 xml, json, ADO ResultSet 에 따라 달라질 수 있으니 Interface?
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outputDataParse() {  //이건 xml, json, ADO ResultSet 에 따라 달라질 수 있으니 Interface?
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendResponse() {  //view로 전송하는 단계이니 추상클래스?
		// TODO Auto-generated method stub
		
	}
	
	// 파라메터 유효성 검사.
	private String checkValidParam(String item, Element root, Document document) {
		String TempStr = "";
		NodeList childSchema = root.getChildNodes();
		for (int i = 0; i < childSchema.getLength(); i++){
			Node nodeSchema = childSchema.item(i);

			if (nodeSchema.getNodeType() == Node.ELEMENT_NODE){
				Element elementSchema = (Element)nodeSchema;

				if (elementSchema.getNodeName().equals(item)){
					TempStr = document.getElementsByTagName(item).item(0).getTextContent();
					break;
				}
			}
		}
		return TempStr;
	}

}
