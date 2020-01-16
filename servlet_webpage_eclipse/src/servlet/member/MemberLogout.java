package servlet.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogout
 */
@WebServlet("/MemberLogout")
public class MemberLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberLogout() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String name = (String)session.getAttribute("session_name");
		session.invalidate();
		
		String msg = name+"님 로그아웃 되었습니다..";
		String url = "/Index";
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		RequestDispatcher rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		rdp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}