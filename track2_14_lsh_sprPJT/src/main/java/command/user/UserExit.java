package command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import dao.User_dao;

public class UserExit implements CommandUser {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		User_dao dao = new User_dao();
		String id = request.getParameter("t_id");
		int result = dao.exitUser(id);
		String msg = "";
		String url = "/";
		if(result == 0) {
			msg = "ȸ��Ż�� ������ �߻��߽��ϴ�.";
		} else {
			msg = "���������� ȸ��Ż��Ǿ����ϴ�.";
			HttpSession session = request.getSession();
			session.invalidate();
		}
		model.addAttribute("t_url", url);
		model.addAttribute("t_msg", msg);
	}
}