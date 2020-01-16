package common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Index() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
//		String sessionName  = (String)session.getAttribute("session_name");
//		String sessionId    = (String)session.getAttribute("session_id");	
//		String sessionLevel = (String)session.getAttribute("session_level");	
//		
//		request.setAttribute("sessionId", sessionId);
//		request.setAttribute("sessionName", sessionName);
//		request.setAttribute("sessionLevel", sessionLevel);
		
		RequestDispatcher rdp = request.getRequestDispatcher("/index/index.jsp");
		rdp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}