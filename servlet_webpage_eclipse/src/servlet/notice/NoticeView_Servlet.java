package servlet.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Notice_DAO;
import dto.Notice_DTO;

/**
 * Servlet implementation class NoticeView_Servlet
 */
@WebServlet("/NoticeView_Servlet")
public class NoticeView_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeView_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Notice_DAO dao = new Notice_DAO();
		String notice_no = request.getParameter("t_noticeNo");
		int nHit 		 = dao.noticeHit_servlet(notice_no);
		Notice_DTO dto 	 = dao.getNoticeView_serlvet(notice_no);
		request.setAttribute("t_dto", dto);
		RequestDispatcher rdp = request.getRequestDispatcher("/notice/notice_view.jsp");
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