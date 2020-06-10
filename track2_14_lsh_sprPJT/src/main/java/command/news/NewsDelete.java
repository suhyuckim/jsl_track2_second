package command.news;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.News_dao;

public class NewsDelete implements CommandNews {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		News_dao dao = new News_dao();
		
		String no  = request.getParameter("t_no");
		int result = dao.deleteNews(no);
		String url = "/News";
		String msg = "";
		
		if(result == 0) msg = "삭제 실패하였습니다.";
		else msg = "정상적으로 삭제되었습니다.";
		
		model.addAttribute("t_msg", msg);
		model.addAttribute("t_url", url);
	}
}
