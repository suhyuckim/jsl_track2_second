package servlet.event;

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
import dao.Event_Apply_DAO;
import dto.Event_Apply_DTO;

/**
 * Servlet implementation class EventApply_Servlet
 */
@WebServlet("/EventApply_Servlet")
public class EventApply_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventApply_Servlet() {
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
			Event_Apply_DAO dao 	= new Event_Apply_DAO();
			String event_no 	= request.getParameter("t_event_no");
			String event_m_no 	= dao.getMaxId(event_no);	
			String title 		= request.getParameter("t_title");	
			String content		= request.getParameter("contents");
			String reg_id 		= request.getParameter("writer");	
			String reg_date 	= CommonUtil.getToday();
			
			Event_Apply_DTO event_apply_dto = new Event_Apply_DTO(event_no, event_m_no, title, content, reg_id, reg_date, "");
			int result = dao.InsertEvent_Apply(event_apply_dto);
			
			if(result > 0) {
				msg = "등록되었습니다.";
				url = "/EventList_Servlet";
			} else {
				msg = "등록 실패.";
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