package api.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;




@WebServlet("/NurionAPI_001.jsp")
public class ApiController {
	
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	public void destroy() {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doHandle(request, response);
		//method "get" is not valid.
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setHeader("Expires", "0");
		
		try {
			String strXml = request.getParameter("strXml");
			
			HashMap<String, Object> htData = new HashMap<String, Object>();
			
			StringReader sr = new StringReader(strXml);
			
			//이 아래로 전부다 인터페이스로 넘기자
			InputSource is = new InputSource(sr);
			
			
			
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
			
			
		}
		
		//RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMembers.jsp");
		//dispatch.forward(request, response);
		
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
