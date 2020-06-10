package command.faq;

import javax.servlet.http.HttpServletRequest;

import dao.Faq_dao;
import dto.Faq_dto;

public class FaqView implements FaqCommand {
	@Override
	public void execute(HttpServletRequest request) {
		Faq_dao dao = new Faq_dao();
		String no = request.getParameter("t_no");
		Faq_dto dto = dao.getFaqView(no);
		request.setAttribute("t_dto", dto);
	}
}