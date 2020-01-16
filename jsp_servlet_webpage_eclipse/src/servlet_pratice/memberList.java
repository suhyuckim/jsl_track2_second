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
 * Servlet implementation class memberList
 */
@WebServlet("/memberList")
public class memberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public memberList() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Smember_DAO dao = new Smember_DAO();
		ArrayList<Smember_DTO> dtos = dao.getSmemberList();
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_name", "홍길동");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/servletFolder/member_r.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}