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

import api.model.ApiADO;




@WebServlet("/NurionAPI_KUMC_AA.jsp")
public class ApiADOController {
	
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
			
			StringReader sr = new StringReader(strXml);
			
			//이 아래로 전부다 인터페이스로 넘기자
			
			ApiADO apiAdo = new ApiADO(sr, request, response);
			
			apiAdo.getRequest();  //여기서 리턴 받는걸 view 로 넘기자.
			
		} catch (Exception e) {
			
			
		}
		
		//RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMembers.jsp");
		//dispatch.forward(request, response);
		
	}
	
	
	
}
