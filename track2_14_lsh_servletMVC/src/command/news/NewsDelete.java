package command.news;

import javax.servlet.http.HttpServletRequest;

import dao.News_dao;

public class NewsDelete implements NewsAcommand{
	@Override
	public void execute(HttpServletRequest request){
		News_dao dao = new News_dao();
		
		String no  = request.getParameter("t_no");
		int result = dao.deleteNews(no);
		
		String url = request.getContextPath()+"/News";
		String msg = "";
		if(result == 0) msg =" News 삭제 실패~";
		else msg ="정상적으로 삭제되었습니다.";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}
}