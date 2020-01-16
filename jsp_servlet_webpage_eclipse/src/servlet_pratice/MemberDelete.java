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
 * Servlet implementation class MemberDelete
 */
@WebServlet("/MemberDelete")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberDelete() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Smember_DAO dao = new Smember_DAO();
		String id = request.getParameter("t_id");
		int result = dao.getMemberDelete(id);
		
		if(result > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
		response.sendRedirect("/memberList");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}