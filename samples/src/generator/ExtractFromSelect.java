package generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExtractFromSelect {
	public static void main(String[] args) {
		Connection conn = null; 
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ResultSetMetaData rsmd = null;
		try {	
			String sqlText = "";
			
			//sqlText += "Select A.EEA_EXAM_DT AS WOW, B.ERI_EXAM_SQ AS SEQ from ET_EXAM_ACPT A ";
			//sqlText += " Inner join ET_RSLT_ITEM B on A.EEA_EXAM_DT = B.ERI_EXAM_DT and A.EEA_EXAM_SQ = B.ERI_EXAM_SQ and 1=0";
			
			sqlText += "select * from it_hospital";
			
			conn = DbCon.getConnection();
			pstm = conn.prepareStatement(sqlText);
			rs = pstm.executeQuery();
			
			rsmd = rs.getMetaData();
			
			int columnCnt = rsmd.getColumnCount();  //컬럼 갯수
			
//			while(rs.next()) {
//				System.out.println(rs.getString("IHL_HOSP_NM"));
//			}
			
			for (int i = 1; i <= columnCnt; i++) {
				System.out.println("ColumnName : " + rsmd.getColumnName(i));
				System.out.println("Size : " + rsmd.getColumnDisplaySize(i));
				System.out.println("TypeName : " + rsmd.getColumnTypeName(i));
				System.out.println("Type : " + Integer.toString(rsmd.getColumnType(i)));
				System.out.println("Scale : " + Integer.toString(rsmd.getScale(i)));
				System.out.println("========");
			}
			
			System.out.println(Integer.toString(columnCnt));
			
		} catch (SQLException sqle) {
			System.out.println("error : " + sqle);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (pstm != null) {pstm.close();}
				if (conn != null) {conn.close();}
			} catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
