package command.faq;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.Faq_dao;
import dto.Faq_dto;

public class CommandFaqList implements CommandFaq {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		Faq_dao dao = new Faq_dao();
		ArrayList<Faq_dto> dtos = dao.getFaqList();
		model.addAttribute("t_dtos", dtos);
	}
}