package servlet_pratice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Notice_DAO;
import dto.Notice_DTO;

/**
 * Servlet implementation class test_1
 */
@WebServlet("/test_1")
public class test_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public test_1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿~~~");
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("t_name");
		request.setAttribute("tt_name", name);
		
		Notice_DTO dto = new Notice_DTO();
		Notice_DAO dao = new Notice_DAO();
		dto = dao.getNoticeView(name);
		request.setAttribute("t_dto",dto);
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("가");
		arr.add("나");
		request.setAttribute("t_arr",arr);
				
		RequestDispatcher rdp = request.getRequestDispatcher("/servletFolder/testjsp_3.jsp");
		rdp.forward(request, response); 
		
		System.out.println("name : "+name);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}