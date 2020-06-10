package command.news;

import javax.servlet.http.HttpServletRequest;
import dao.News_dao;
import dto.News_dto;

public class NewsView implements NewsAcommand{
	@Override
	public void execute(HttpServletRequest request){
		News_dao dao = new News_dao();
		String no = request.getParameter("t_no");
		String gubun = request.getParameter("t_gubun");
		if(gubun.equals("vidw")) dao.hitCount(no);
		News_dto dto = dao.getNewsView(no);
		request.setAttribute("t_dto", dto);	
	}	
}