package servlet.news;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CommonUtil;
import dao.News_DAO;
import dao.Notice_DAO;

/**
 * Servlet implementation class NewsDelete
 */
@WebServlet("/NewsDelete")
public class NewsDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NewsDelete() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		String session_level = (String)session.getAttribute("session_level");
		RequestDispatcher dispatcher = null;
		
		String msg="";
		String url="";
		
		if(session_level == null) {
			msg="로그인 정보가 만료되었습니다.";
			url="/Index";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		} else {
			News_DAO dao 	 = new News_DAO();	
			String news_no = request.getParameter("t_news_no");	
			String fileName  = request.getParameter("t_fileName");
			String file_dir  = CommonUtil.file_dir_notice;
			int result 		 = dao.deleteNews(news_no);	
	
			if(fileName != null){
				File dFa = new File(file_dir, fileName);
				dFa.delete();
			}
			
			if(result > 0) {
				msg = "삭제되었습니다.";
				url = "/NewsList";
			} else {
				msg = "삭제 실패.";
				url = "/NewsList";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		}
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}