package servlet.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_DAO;
import dao.Notice_DAO;
import dto.Member_DTO;
import dto.Notice_DTO;

/**
 * Servlet implementation class MemberView_Servlet
 */
@WebServlet("/MemberView_Servlet")
public class MemberView_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberView_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member_DAO dao = new Member_DAO();
		String id = request.getParameter("t_memberNo");
		Member_DTO dto 	 = dao.getMemberInfo_servlet(id);
		request.setAttribute("t_dto", dto);
		RequestDispatcher rdp = request.getRequestDispatcher("/member/member_view.jsp");
		rdp.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
