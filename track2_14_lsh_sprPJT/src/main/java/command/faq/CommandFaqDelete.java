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
		
		if(result == 0) msg = "삭제 실패하였습니다.";
		else msg = "정상적으로 삭제되었습니다.";
		
		model.addAttribute("t_msg", msg);
		model.addAttribute("t_url", "/Faq");
	}
}