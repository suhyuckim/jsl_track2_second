package command.news;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import common.CommonUtil;
import dao.News_dao;
import dto.News_dto;

public class NewsSave implements CommandNews{
	
	@Override
	public void execute(HttpServletRequest request, Model model) {
		boolean goYN = CommonUtil.getCheckManager(request, model);
		if(goYN) {
			News_dao dao 	= new News_dao();
			String no 		= dao.getNewsNo();
			String title 	= request.getParameter("t_title");
			String content 	= request.getParameter("t_content");
			String reg_info = CommonUtil.getSessionId(request);
			String reg_date = CommonUtil.getToday();
			int result = dao.saveNews(no,title,content,reg_info,reg_date);
			String msg="";
			if(result == 0) msg="등록 실패하였습니다.";
			else msg="정상적으로 등록되었습니다.";
			model.addAttribute("t_msg", msg);
			model.addAttribute("t_url", "/News");
		}
	}
}