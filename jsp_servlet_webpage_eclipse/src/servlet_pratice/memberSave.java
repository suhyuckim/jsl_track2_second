package servlet_pratice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Smember_DAO;
import dto.Smember_DTO;

/**
 * Servlet implementation class memberSave
 */
@WebServlet("/memberSave")
public class memberSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public memberSave() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id 	= request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String area = request.getParameter("t_area");
		String age  = request.getParameter("t_age");
		int t_age   = Integer.parseInt(age);
		
		System.out.println("id : "+id);
		System.out.println("name : "+name);
		System.out.println("area : "+area);
		System.out.println("age : "+age);
		
		Smember_DTO dto = new Smember_DTO(id, name, area, t_age);
		Smember_DAO dao = new Smember_DAO();
		int result = dao.insertSmember(dto);
	
		if(result > 0) {
			System.out.println("등록 완료");
		} else {
			System.out.println("등록 실패");
		}
//		RequestDispatcher dis = request.getRequestDispatcher("/servletFolder/member_r.jsp");
//		dis.forward(request, response);
		response.sendRedirect("/memberList");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}