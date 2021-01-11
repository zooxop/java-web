package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItHospitalServlet
 */
@WebServlet("/ithospital")
public class ItHospitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ItHospitalDAO dao = new ItHospitalDAO();
		List<ItHospitalVO> list = dao.listHospital();
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>병원명</td><td>요양기관기호</td><td>대표자명</td></tr>");
		
		for (int i = 0; i < list.size(); i++) {
			ItHospitalVO hospVO = list.get(i);
			String ihl_hosp_nm = hospVO.getIhl_hosp_nm();
			String ihl_hosp_no = hospVO.getIhl_hosp_no();
			String ihl_pres_nm = hospVO.getIhl_pres_nm();
			out.print("<tr><td>" + ihl_hosp_nm + "</td><td>" + ihl_hosp_no + "</td><td>" + ihl_pres_nm + "</td></tr>");
			
		}
		out.print("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
