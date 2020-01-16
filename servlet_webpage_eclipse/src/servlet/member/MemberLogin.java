package servlet.member;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommonUtil;
import dao.Member_DAO;

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/MemberLogin")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberLogin() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member_DAO dao  = new Member_DAO();
		String id   	= request.getParameter("id");
		String pw 		= request.getParameter("pw");
		String s_pw = "";
		try {
			s_pw = CommonUtil.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = dao.checkLogin_servlet(id, s_pw);
		String msg  = "";
		String url  = "";
		RequestDispatcher rdp = null;
		if(name == null) {
			msg = "ID 또는 비밀번호 오류입니다. 다시 입력해 주세요";
			url = "/MemberLoginForm";			
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("session_name",name);
			session.setAttribute("session_id",id);
			if(id.equals("manager")){
				session.setAttribute("session_level","manager");
			}
			session.setMaxInactiveInterval(60*60*1);
			msg = name+"님 환영합니다.";
			url = "/Index";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		rdp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}