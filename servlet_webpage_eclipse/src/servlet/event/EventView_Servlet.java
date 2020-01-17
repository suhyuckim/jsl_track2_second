package servlet.event;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Event_DAO;
import dao.Event_Apply_DAO;
import dto.Event_DTO;

/**
 * Servlet implementation class EventView_Servlet
 */
@WebServlet("/EventView_Servlet")
public class EventView_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EventView_Servlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Event_DAO dao = new Event_DAO();
		Event_Apply_DAO dao_apply = new Event_Apply_DAO();
		String event_no = request.getParameter("t_eventNo");
		String reg_id = request.getParameter("writer");
		String membercount = dao_apply.EventMemberCount(event_no, reg_id);
		int nHit 		 = dao.eventHit_servlet(event_no);
		Event_DTO dto 	 = dao.getEventView_servlet(event_no);
		request.setAttribute("t_dto", dto);
		RequestDispatcher rdp = request.getRequestDispatcher("/event/event_view.jsp");
		rdp.forward(request, response);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}