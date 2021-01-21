package api;

//model 개발 시 상속시킬 추상 클래스
abstract class API3Tier {
	String DataType;  //xml, json 등 구분.
	
	public String getDataType() {
		return DataType;
	}
	public void setDataType(String dataType) {
		DataType = dataType;
	}
	
	public abstract void dbConn();  //각 병원별, DB종류별 connection. (use ConnectionPool)
	public abstract void getRequest();  //xml, json 등 요청 데이터 받아오기
	public abstract void inputDataParse();  //xml, json 등 요청 데이터 파싱 
	public abstract void outputDataParse(); //xml, json 등 리턴 데이터 파싱
	public abstract void sendResponse(); //xml, json 등 응답 데이터 전송
	
}
