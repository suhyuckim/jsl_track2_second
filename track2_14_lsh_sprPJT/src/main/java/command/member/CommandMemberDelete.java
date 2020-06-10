package command.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.Member_dao;

public class CommandMemberDelete implements CommandMember {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		int result = dao.deleteMember(id);
		if(result == 0) System.out.println("삭제 실패하였습니다.");
	}
}