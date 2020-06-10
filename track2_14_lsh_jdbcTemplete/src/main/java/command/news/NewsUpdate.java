package command.news;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import common.CommonUtil;
import dao.News_dao;

public class NewsUpdate implements CommandNews{
	
	@Override
	public void execute(HttpServletRequest request, Model model) {
		boolean goYN = CommonUtil.getCheckManager(request, model);
		if(goYN) {
			News_dao dao = new News_dao();
			String no = request.getParameter("t_no");
			String title = request.getParameter("t_title");
			String content = request.getParameter("t_content");
			int result = dao.updateNews(no,title,content);
			
			String msg="";
			if(result == 0) msg="���� �����Ͽ����ϴ�.";
			else msg="���������� �����Ǿ����ϴ�.";
			model.addAttribute("t_msg", msg);
			model.addAttribute("t_url", "/News");
		}
	}
}