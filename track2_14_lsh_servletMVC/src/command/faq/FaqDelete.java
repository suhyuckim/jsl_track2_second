package command.faq;

import javax.servlet.http.HttpServletRequest;

import dao.Faq_dao;

public class FaqDelete implements FaqCommand {

	@Override
	public void execute(HttpServletRequest request) {
		Faq_dao dao = new Faq_dao();
		
		String no  = request.getParameter("t_no");
		int result = dao.deleteNews(no);
		String msg = "";
		
		String url = request.getContextPath()+"/Faq";
		if(result == 0) msg = "���� �����Ͽ����ϴ�.";
		else msg = "���������� �����Ǿ����ϴ�.";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}
}