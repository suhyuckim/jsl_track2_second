package command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import dao.User_dao;

public class UserLogin implements CommandUser {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		User_dao dao = new User_dao();
		String id 	 = request.getParameter("t_id");
		String pw 	 = request.getParameter("t_pw");
		
		String name  = dao.getLoginName(id,pw);
	
		String msg = "";
		String url = "";
		
		if(name.equals("")) {
			msg = "ID나 비밀번호가 일치하지 않습니다.";
			url = "/User";
		} else {
			msg = name+"님 환영합니다.";
			url = "/";
			HttpSession session = request.getSession();
			session.setAttribute("session_id", id);
			session.setAttribute("session_name", name);
			if(id.equals("manager")) {
				session.setAttribute("session_level", "manager");
			}
			session.setMaxInactiveInterval(60*30);
		}
		model.addAttribute("t_msg", msg);
		model.addAttribute("t_url", url);
	}
}