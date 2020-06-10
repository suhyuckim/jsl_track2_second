package command.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.Member_dao;
import dto.Member_dto;

public class CommandMemberUpdate implements CommandMember {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String area = request.getParameter("t_area");
		String age = request.getParameter("t_age");
		Member_dto dto = new Member_dto(id, name, area, Integer.parseInt(age));
		int result = dao.updateMember(dto);
		if(result == 0) System.out.println("수정을 실패하였습니다.");
	}
}