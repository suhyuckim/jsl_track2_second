package servlet.event;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommonUtil;
import dao.Event_DAO;

/**
 * Servlet implementation class EventDelete_Servlet
 */
@WebServlet("/EventDelete_Servlet")
public class EventDelete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventDelete_Servlet() {
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
		
		String msg="";
		String url="";
		
		if(session_level == null) {
			msg="로그인 정보가 만료되었습니다.";
			url="/Index";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		} else {
			Event_DAO dao 	 = new Event_DAO();	
			String event_no  = request.getParameter("t_event_no");	
			String fileName  = request.getParameter("t_fileName");
			String file_dir  = CommonUtil.file_dir_notice;
			int result 		 = dao.deleteEvent_servlet(event_no);	
			if(fileName != null){
				File dFa = new File(file_dir, fileName);
				dFa.delete();
			}
			if(result > 0) {
				msg = "삭제되었습니다.";
				url = "/EventList_Servlet";
			} else {
				msg = "삭제 실패.";
				url = "/EventList_Servlet";
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
