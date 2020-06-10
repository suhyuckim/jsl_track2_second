package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import dao.Member_dao;

public class MemberLogin {
	public void execute(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		
		String id = request.getParameter("t_id");
		String pw = request.getParameter("t_pw");
		String pwPass = "";
		try {
			pwPass = dao.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String name = dao.getCheckLogin(id,pwPass);
		
		if(name.equals("")) {
			model.addAttribute("t_msg", "아이디나 비밀번호가 정확하지 않습니다.");
			model.addAttribute("t_url", "/Member");
		} else {
			model.addAttribute("t_msg", name+"님 환영합니다.");
			model.addAttribute("t_url", "/");
			HttpSession session = request.getSession();
			session.setAttribute("session_id", id);
			session.setAttribute("session_name", name);
			if(id.equals("manager")) {
				session.setAttribute("session_level", "top");
			} else {
				session.setAttribute("session_level", "user");
			}
			session.setMaxInactiveInterval(60*10);
		}
	}
}