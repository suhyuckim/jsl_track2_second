package servlet_faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Faq_DAO;
import dao.Notice_DAO;
import dto.Faq_DTO;
import dto.Notice_DTO;

/**
 * Servlet implementation class FaqUpdateForm_Servlet
 */
@WebServlet("/FaqUpdateForm_Servlet")
public class FaqUpdateForm_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateForm_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String session_level = (String)session.getAttribute("session_level");
		RequestDispatcher dispatcher = null;
	
		if(session_level == null) {
			String msg="로그인 정보가 만료되었습니다.";
			String url="/Index";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		} else {
			Faq_DAO dao = new Faq_DAO();
			String faq_id 	= request.getParameter("faq_id");
			Faq_DTO dto 	= dao.getFaqView_serlvet(faq_id);
			request.setAttribute("t_dto", dto);
			dispatcher = request.getRequestDispatcher("/faq/faq_update.jsp");
		}
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}