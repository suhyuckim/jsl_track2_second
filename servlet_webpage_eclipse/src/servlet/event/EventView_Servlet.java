package servlet.event;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Event_DAO;
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
		String event_no = request.getParameter("t_eventNo");
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