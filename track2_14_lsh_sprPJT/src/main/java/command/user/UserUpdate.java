package command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import common.CommonUtil;
import dao.User_dao;
import dto.User_dto;

public class UserUpdate implements CommandUser {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		User_dao dao   = new User_dao();
		String id 	   = request.getParameter("t_id");
		String pw 	   = request.getParameter("t_pw_1");
		String name    = request.getParameter("t_name");
		String tel     = request.getParameter("t_tel");
		String email_1 = request.getParameter("t_email_1");
		String email_2 = request.getParameter("t_email_2");
		String info_yn = request.getParameter("t_info_yn");
		
		User_dto dto = new User_dto(id,pw,name,tel,email_1,email_2,info_yn,"","","");
		int result = dao.updateUser(dto);
		
		String msg = "";
		if(result == 0) {
			msg="ȸ������ ���� �����Ͽ����ϴ�.";
		} else {
			msg= name+"�� ���������� ȸ������ �����Ǿ����ϴ�.";
			HttpSession session = request.getSession();
			session.setAttribute("session_name", name);
		}
		model.addAttribute("t_msg", msg);
		model.addAttribute("t_url", "/");
	}
}