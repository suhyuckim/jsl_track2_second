package command.faq;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.Faq_dao;

public class CommandFaqDelete implements CommandFaq {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		Faq_dao dao = new Faq_dao();
		
		String no  = request.getParameter("t_no");
		int result = dao.deleteNews(no);
		String msg = "";
		
		if(result == 0) msg = "���� �����Ͽ����ϴ�.";
		else msg = "���������� �����Ǿ����ϴ�.";
		
		model.addAttribute("t_msg", msg);
		model.addAttribute("t_url", "/Faq");
	}
}