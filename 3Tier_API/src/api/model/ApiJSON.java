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

public class ApiJSON extends API3Tier{
	
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

	public ApiJSON(StringReader stringReader, HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
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
			//JSON (GSON) 사용하기
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
