package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.news.NewsAcommand;
import command.news.NewsDelete;
import command.news.NewsList;
import command.news.NewsSave;
import command.news.NewsUpdate;
import command.news.NewsView;

/**
 * Servlet implementation class News
 */
@WebServlet("/News")
public class News extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public News() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun ="list";
		String viewPage ="";
//		boolean goMethod = true;
		if(gubun.equals("list")) {
			NewsAcommand news = new NewsList();
			news.execute(request);
			viewPage = "/news/news_list.jsp";
			
		} else if(gubun.equals("view")) {
			NewsAcommand news = new NewsView();
			news.execute(request);
			viewPage = "/news/news_view.jsp";
			
		} else if(gubun.equals("save")) {
//			goMethod = false;
			NewsAcommand news = new NewsSave();
			news.execute(request);
			viewPage = "/common_alert_page.jsp";
//			response.sendRedirect(request.getContextPath()+"/News");
			
		} else if(gubun.equals("writeForm")) {			
			viewPage = "/news/news_write.jsp";
			
		} else if(gubun.equals("update")) {		
//			goMethod = false;
			NewsAcommand news = new NewsUpdate();
			news.execute(request);
			viewPage = "/common_alert_page.jsp";
//			response.sendRedirect(request.getContextPath()+"/News");	
			
		} else if(gubun.equals("updateForm")) {
			NewsAcommand news = new NewsView();
			news.execute(request);
			viewPage = "/news/news_update.jsp";

		} else if(gubun.equals("delete")) {	
//			goMethod = false;
			NewsAcommand news = new NewsDelete();
			news.execute(request);
			viewPage = "/common_alert_page.jsp";			
		}
//		if(goMethod){
			RequestDispatcher rd = request.getRequestDispatcher(viewPage);
			rd.forward(request, response);
//		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
