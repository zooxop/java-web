package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserVO extends HttpServlet {
	private String iau_emp_no;
	private String iau_penl_nm;
	
	public UserVO() {
		System.out.println("called UserVO Constructor");
	}
	
	public UserVO(String iau_emp_no, String iau_penl_nm) {
		this.iau_emp_no = iau_emp_no;
		this.iau_penl_nm = iau_penl_nm;
	}

	public String getIau_emp_no() {
		return iau_emp_no;
	}

	public void setIau_emp_no(String iau_emp_no) {
		this.iau_emp_no = iau_emp_no;
	}

	public String getIau_penl_nm() {
		return iau_penl_nm;
	}

	public void setIau_penl_nm(String iau_penl_nm) {
		this.iau_penl_nm = iau_penl_nm;
	}

}
