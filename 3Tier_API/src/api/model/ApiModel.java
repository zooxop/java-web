package api.model;

import javax.sql.DataSource;

import api.API3Tier;

public abstract class ApiModel extends API3Tier{

	@Override
	public void getRequest() {  //이건 추상이 맞다.
		
		DataSource dataFactory = dbConn();  //이런식으로 디비 연결?

		
		
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
