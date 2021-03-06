package servlet_faq;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import common.CommonUtil;
import dao.Faq_DAO;
import dao.Notice_DAO;
import dto.Faq_DTO;
import dto.Notice_DTO;

/**
 * Servlet implementation class FaqSave_Servlet
 */
@WebServlet("/FaqSave_Servlet")
public class FaqSave_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqSave_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		String session_level = (String)session.getAttribute("session_level");
		RequestDispatcher dispatcher = null;
		String msg = "";
		String url = "";
		
		if(session_level == null) {
			msg="로그인 정보가 만료되었습니다.";
			url="/Index";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		} else {
			Faq_DAO dao = new Faq_DAO();
			String faq_id 		= dao.getFaqNo();	
			String question 	= request.getParameter("question");	
			String answer		= request.getParameter("answer");
			String reg_id 		= request.getParameter("writer");	
			String reg_date 	= CommonUtil.getToday();

			Faq_DTO faq_dto = new Faq_DTO(faq_id, question, answer, reg_id, reg_date);
			int result = dao.insertFaq_servlet(faq_dto);
			
			if(result > 0) {
				msg = "등록되었습니다.";
				url = "/FaqList_Servlet";
			} else {
				msg = "등록 실패.";
				url = "/FaqList_Servlet";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
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