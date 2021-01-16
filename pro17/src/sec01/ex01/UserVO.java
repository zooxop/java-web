package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserVO extends HttpServlet {
	
	public UserVO() {
		System.out.println("called UserVO Constructor");
	}
	
	public UserVO(String iau_emp_no, String iau_penl_nm) {
		
	}

}
