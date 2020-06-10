package command.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.User_dao;
import dto.User_dto;

public class CommandMemberView implements CommandManager {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		User_dao dao = new User_dao();
		String id = request.getParameter("t_id");
		User_dto dto = dao.getMemberView(id);
		model.addAttribute("t_dto", dto);
	}
}