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
			msg = "ID�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			url = "/User";
		} else {
			msg = name+"�� ȯ���մϴ�.";
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