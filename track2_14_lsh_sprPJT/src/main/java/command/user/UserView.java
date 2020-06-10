package command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import dao.User_dao;
import dto.User_dto;

public class UserView implements CommandUser {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		User_dao dao = new User_dao();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("session_id");
		String msg = "";
		String url = "";
		if(id == null) {
			msg = "�α��� ������ �����ϴ�. �ٽ� �α��� �ϼ���~";
			url = "/User";
		} else {
			User_dto dto = dao.getUserInfo(id);
			request.setAttribute("t_dto", dto);
		}
		request.setAttribute("t_url", url);
		request.setAttribute("t_msg", msg);
	}
}