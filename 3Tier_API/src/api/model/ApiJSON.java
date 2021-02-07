package api.model;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import api.API3Tier;
import api.VO.PtntInfoURLVO;

public class ApiJSON extends API3Tier{
	
	private String JsonStr;
	private Connection conn;
	
	public void setJsonStr(String jsonStr) {
		JsonStr = jsonStr;
	}

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


	public ApiJSON(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
		setRequest(request);
		setResponse(response);
	}
	
	@Override
	public void getRequest() {  //이건 추상이 맞다.
		
		DataSource dataFactory = dbConn();  //이런식으로 디비 연결?
		 
		String reqPath = request.getServletPath();
		
		try {
			
			if (reqPath.equals("/ptntInfoURL")) {
				PtntInfoURLVO pInfo = new PtntInfoURLVO();
				
				String RESNO1 = request.getParameter("RESNO1");
				String RESNO2 = request.getParameter("RESNO2");
				
				conn = dataFactory.getConnection();
				
				//인적정보 쿼리한 다음에 VO에 set 하고 json으로 파싱시켜서 출력
			} else if(reqPath.equals("/ordrInfoURL")) {
				
			}
			
			//JSON (GSON) 사용하기
			JsonParser Parser = new JsonParser();
			
			JsonObject jsonObj = (JsonObject) Parser.parse(JsonStr);
			
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
	
	

}
