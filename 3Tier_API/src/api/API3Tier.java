package api;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//model 개발 시 상속시킬 추상 클래스
public abstract class API3Tier {
	String DataType;  //xml, json 등 구분.
	DataSource dataFactory;

	public String getDataType() {
		return DataType;
	}
	public void setDataType(String dataType) {
		DataType = dataType;
	}
	
	public DataSource dbConn() {
		//DB종류(oracle, tibero) 상관없이 connection. (use ConnectionPool)
		
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");  //oracle 명칭은 바꾸기만 하면 됨.
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return dataFactory;
	}
	
	public abstract void getRequest();  //xml, json 등 요청 데이터 받아오기
	public abstract void inputDataParse();  //xml, json 등 요청 데이터 파싱 
	public abstract void outputDataParse(); //xml, json 등 리턴 데이터 파싱
	public abstract void sendResponse(); //xml, json 등 응답 데이터 전송
	
}
