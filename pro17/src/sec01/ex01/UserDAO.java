package sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class UserDAO extends HttpServlet {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public UserDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<UserVO> listUsers(){
		List<UserVO> usersList = new ArrayList<UserVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "Select * from IT_AUTH_USER";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String iau_emp_no = rs.getString("IAU_EMP_NO");
				String iau_penl_nm = rs.getString("IAU_PENL_NM");
				
				UserVO userVO = new UserVO(iau_emp_no, iau_penl_nm);
				usersList.add(userVO);
				
			}
			
			if (rs == null) {rs.close();}
			if (pstmt == null) {pstmt.close();}
			if (conn == null) {conn.close();}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return usersList;
	}
	
	


}



