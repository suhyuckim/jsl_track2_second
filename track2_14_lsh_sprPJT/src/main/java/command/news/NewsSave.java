package command.news;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import common.CommonUtil;
import dao.News_dao;
import dto.News_dto;

public class NewsSave implements CommandNews {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		News_dao dao = new News_dao();
		String no 		= dao.getNewsNo();
		String title 	= request.getParameter("t_title");
		String content  = request.getParameter("t_content");
		String reg_info = "manager";
		String reg_date = CommonUtil.getToday();
		News_dto dto = new News_dto(no,title,content,"",reg_info,reg_date);
		int result = dao.saveNews(dto);
		String msg = "";
		if(result == 0) msg = "등록 실패하였습니다.";
		else msg = "성공적으로 등록되었습니다.";
		
		model.addAttribute("t_url", "/News");
		model.addAttribute("t_msg", msg);
	}
}
