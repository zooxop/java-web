package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user.do")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDAO userDAO;

	public void init(ServletConfig config) throws ServletException {
		userDAO = new UserDAO(); 
	}

	public void destroy() {
		//destroy
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<UserVO> userList = userDAO.listUsers();
		request.setAttribute("userList", userList);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMembers.jsp");
		
		dispatch.forward(request, response);
	}
	

}
