package command.news;

import javax.servlet.http.HttpServletRequest;

import dao.News_dao;

public class NewsUpdate implements NewsAcommand{
	@Override
	public void execute(HttpServletRequest request){
		News_dao dao = new News_dao();
		String no 		= request.getParameter("t_no");
		String title 	= request.getParameter("t_title");
		String content 	= request.getParameter("t_content");
		
		int result = dao.updateNews(no,title,content);
		
		String url = request.getContextPath()+"/News";
		String msg = "";
		if(result == 0) msg =" News ���� ����~";
		else msg = "���������� �����Ǿ����ϴ�. ";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}
}
