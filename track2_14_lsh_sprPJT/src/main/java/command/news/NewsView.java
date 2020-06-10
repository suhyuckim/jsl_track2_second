package command.news;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.News_dao;
import dto.News_dto;

public class NewsView implements CommandNews {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		News_dao dao = new News_dao();
		String no = request.getParameter("t_no");
		News_dto dto = dao.getNewsView(no);
		
		model.addAttribute("t_dto", dto);
	}
}