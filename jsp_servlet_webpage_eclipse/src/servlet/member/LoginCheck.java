package servlet.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member_DAO;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginCheck() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member_DAO dao = new Member_DAO();
		String id   = request.getParameter("id");
		String pw   = request.getParameter("pw");
		String name = dao.checkLogin(id, pw);
		String msg  = "";
		String url  = "";
		RequestDispatcher rdp = null;
		if(name == null) {
			msg = "ID 또는 비밀번호 오류~~";
			url = "/LoginForm";			
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("session_name",name);
			session.setAttribute("session_id",id);
			if(id.equals("manager")){
				session.setAttribute("session_level","manager");
			}
			session.setMaxInactiveInterval(60*30);
			msg = name+"님 환영합니다.";
			url = "/Index";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		rdp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}