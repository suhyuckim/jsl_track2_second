package command.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.Member_dao;
import dto.Member_dto;

public class CommandMemberView implements CommandMember {
	@Override
	public void execute(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		Member_dto dto = dao.getMemberView(id);
		model.addAttribute("t_dto", dto);
	}
}