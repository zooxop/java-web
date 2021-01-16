package pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TestDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	private ResultSet rs;
	private ResultSetMetaData rsmd;

	/*
	String jdbc_driver = "oracle.jdbc.OracleDriver";
	String db_url = "jdbc:oracle:thin:@localhost:1521:XE";
	*/
	
	//생성자
	public TestDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	public String openQuery(String query) {
		String rtnXml = "";
		
		
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			
			
			rtnXml = createSchemaXml();
			System.out.println(rtnXml);
			
			/*while (rs.next()) {
				rtnXml = rs.getString("IHL_HOSP_NO");
			}*/
			
			closeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rtnXml;
	}
	
	public String createSchemaXml() {
		StringBuffer sb = new StringBuffer();
		Set<String> fieldNameSet = new HashSet<String>();
		Set<String> fieldTypeNameSet = new HashSet<String>();
		try {
			
			int colCnt;
			
			sb.append("<nurionXml>\n");
			sb.append("<resultCode>200</resultCode>\n");
			sb.append("<resultXml>\n");
			sb.append("<xml xmlns:s='uuid:BDC6E3F0-6DA3-11d1-A2A3-00AA00C14882'\n");
			sb.append("xmlns:dt='uuid:C2F41010-65B3-11d1-A29F-00AA00C14882'\n");
			sb.append("xmlns:rs='urn:schemas-microsoft-com:rowset'\n");
			sb.append("xmlns:z='#RowsetSchema'>\n");
			sb.append("<s:Schema id='RowsetSchema'>\n");
			sb.append("<s:ElementType name='row' content='eltOnly' rs:updatable='true'>\n");
			for (colCnt = 1; colCnt <= rsmd.getColumnCount(); colCnt++) {
				fieldNameSet.add(rsmd.getColumnName(colCnt));
				fieldTypeNameSet.add(rsmd.getColumnTypeName(colCnt));
				String dataType = "string";
				String maxLength = "4000";
				if (rsmd.getColumnTypeName(colCnt).equals("BLOB")){
					dataType = "bin.hex";
					maxLength = "2147483647";
					//<s:datatype dt:type='bin.hex' dt:maxLength='2147483647' rs:long='true'/>
				} else if (rsmd.getColumnTypeName(colCnt).equals("CLOB")){
					maxLength = "1073741823";
					//<s:datatype dt:type='string' dt:maxLength='1073741823' rs:long='true'/>
				}
				
				sb.append("<s:AttributeType name='"+rsmd.getColumnName(colCnt)+"' rs:number='"+Integer.toString(colCnt)+"' rs:writeunknown='true' rs:basetable='DUAL' rs:basecolumn='"+rsmd.getColumnName(colCnt)+"'>\n");
				//<s:datatype dt:type='<%= dataType%>' dt:maxLength='<%= maxLength%>' <% if (! maxLength.equals("4000")) { %> rs:long='true' <% } %>/>
				if (! maxLength.equals("4000")) {
					
					sb.append("<s:datatype dt:type='"+dataType+"' dt:maxLength='"+maxLength+"' rs:long='true'/>\n");
				} else {
					sb.append("<s:datatype dt:type='"+dataType+"' dt:maxLength='"+maxLength+"'/>\n");
				}

				sb.append("</s:AttributeType>\n");
			}
			
			sb.append("<s:AttributeType name='ROWID' rs:number='"+Integer.toString(colCnt)+"' rs:rowid='true' rs:writeunknown='true' rs:basetable='DUAL'\n");
			sb.append("rs:basecolumn='ROWID' rs:keycolumn='true' rs:hidden='true' rs:autoincrement='true'>\n");
			sb.append("<s:datatype dt:type='string' rs:dbtype='str' dt:maxLength='18' rs:fixedlength='true'/>\n");
			sb.append("</s:AttributeType>\n");
			sb.append("<s:extends type='rs:rowbase'/>\n");
			sb.append("</s:ElementType>\n");
			sb.append("</s:Schema>\n");
			sb.append("<rs:data> </rs:data>");
			sb.append("</xml>\n");
			sb.append("</resultXml>\n");
			sb.append("<errorMsg/>\n");
			sb.append("</nurionXml>\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	private void closeAll(){
		try{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
            if(conn!=null) conn.close();
            
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
