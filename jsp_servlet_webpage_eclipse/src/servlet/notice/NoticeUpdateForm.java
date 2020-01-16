package servlet.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Notice_DAO;
import dto.Notice_DTO;

/**
 * Servlet implementation class NoticeUpdateForm
 */
@WebServlet("/NoticeUpdateForm")
public class NoticeUpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeUpdateForm() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Notice_DAO dao = new Notice_DAO();
		String notice_no = request.getParameter("t_noticeNo");
		Notice_DTO dto = dao.getNoticeView(notice_no);
		request.setAttribute("t_dto", dto);
		
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
			dispatcher = request.getRequestDispatcher("/notice/notice_u.jsp");
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}