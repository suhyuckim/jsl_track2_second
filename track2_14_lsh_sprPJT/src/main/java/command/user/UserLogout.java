package command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public class UserLogout implements CommandUser {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("session_name");
		session.invalidate();
		String msg = name+"�� �α׾ƿ��Ǿ����ϴ�.";
		String url = "/";
		
		model.addAttribute("t_msg", msg);
		model.addAttribute("t_url", url);
	}
}