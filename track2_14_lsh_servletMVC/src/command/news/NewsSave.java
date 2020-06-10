package command.news;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.CommonUtil;
import dao.News_dao;
import dto.News_dto;

public class NewsSave  implements NewsAcommand{
	@Override
	public void execute(HttpServletRequest request){
		News_dao dao = new News_dao();
		String no 		= dao.getNewsNo();
		String title 	= request.getParameter("t_title");
		String content 	= request.getParameter("t_content");
		String reg_info = "manager";
		String reg_date = CommonUtil.getToday();
		
		News_dto dto = new News_dto(no,title,content,"0",reg_info,reg_date);
		int result = dao.saveNews(dto);
		
		String url = request.getContextPath()+"/News";
		String msg = "";
		if(result == 0) msg =" News 등록 실패~";
		else msg =" 정상적으로 등록되었습니다. ";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}
}












