package command.notice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.Notice_dao;
import dto.Notice_dto;

public class NoticeView implements CommandNotice {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		Notice_dao dao = new Notice_dao();
		String no = request.getParameter("t_no");
		dao.hitCount(no);
		Notice_dto dto = dao.getNoticeView(no);
		
		model.addAttribute("t_dto", dto);
	}
}