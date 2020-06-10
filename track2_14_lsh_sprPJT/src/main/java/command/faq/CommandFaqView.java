package command.faq;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.Faq_dao;
import dto.Faq_dto;

public class CommandFaqView implements CommandFaq {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		Faq_dao dao = new Faq_dao();
		String no = request.getParameter("t_no");
		Faq_dto dto = dao.getFaqView(no);
		model.addAttribute("t_dto", dto);
	}
}