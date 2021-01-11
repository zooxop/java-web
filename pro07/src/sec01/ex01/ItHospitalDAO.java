package sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItHospitalDAO {
	private Statement stmt;
	private Connection con;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@118.67.135.31:1521:xe";
	String user = "ARUME";
	String pwd = "AitToLiss";
	
	public List<ItHospitalVO> listHospital(){
		List<ItHospitalVO> list = new ArrayList<ItHospitalVO>();
		
		try {
			connDB();
			String query = "Select * from IT_HOSPITAL";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String ihl_hosp_nm = rs.getString("IHL_HOSP_NM");
				String ihl_hosp_no = rs.getString("IHL_HOSP_NO");
				String ihl_pres_nm = rs.getString("IHL_PRES_NM");
				
				ItHospitalVO vo = new ItHospitalVO();
				vo.setIhl_hosp_nm(ihl_hosp_nm);
				vo.setIhl_hosp_no(ihl_hosp_no);
				vo.setIhl_pres_nm(ihl_pres_nm);
				
				list.add(vo);
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("loading success for oracle driver");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("create success for Connection");
			stmt = con.createStatement();
			System.out.println("create success for Statement");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}





