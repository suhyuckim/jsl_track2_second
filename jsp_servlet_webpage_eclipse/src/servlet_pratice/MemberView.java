package servlet_pratice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Smember_DAO;
import dto.Smember_DTO;

/**
 * Servlet implementation class MemberView
 */
@WebServlet("/MemberView")
public class MemberView extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberView() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Smember_DAO dao = new Smember_DAO();
		String id = request.getParameter("t_id");
		Smember_DTO dto = dao.getMemberView(id);
		request.setAttribute("t_dto", dto);
		RequestDispatcher rdp = request.getRequestDispatcher("/servletFolder/member_v.jsp");
		rdp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}