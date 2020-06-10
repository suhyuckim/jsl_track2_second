package command.faq;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.Faq_dao;
import dto.Faq_dto;

public class FaqList implements FaqCommand {

	@Override
	public void execute(HttpServletRequest request) {
		Faq_dao dao = new Faq_dao();
		ArrayList<Faq_dto> dtos = dao.getFaqList();
		request.setAttribute("t_dtos", dtos);
	}
}