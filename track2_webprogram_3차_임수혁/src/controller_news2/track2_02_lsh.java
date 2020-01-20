package controller_news2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.News_DAO;
import dto.News_DTO;

/**
 * Servlet implementation class track2_02_lsh
 */
@WebServlet("/track2_02_lsh")
public class track2_02_lsh extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public track2_02_lsh() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun ="list";
		if(gubun.equals("list")) {
			request.setCharacterEncoding("UTF-8");
			News_DAO dao = new News_DAO();
			String selValue = request.getParameter("t_sel"); 
			String txtValue = request.getParameter("t_search");
			if(selValue == null){
				selValue = "title";
				txtValue = "";
			}
				ArrayList<News_DTO> dtos = dao.getNewsList_exam(selValue, txtValue);
				request.setAttribute("t_dtos", dtos);
				request.setAttribute("selValue", selValue);
				request.setAttribute("txtValue", txtValue);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/news/news_list.jsp");
				dispatcher.forward(request, response);
		}else if(gubun.equals("write")) {
			RequestDispatcher rdp = request.getRequestDispatcher("/news/news_write.jsp");
			rdp.forward(request, response);
		}else if(gubun.equals("view")) {
			request.setCharacterEncoding("UTF-8");
			News_DAO dao   = new News_DAO();
			String news_no = request.getParameter("t_newsNo");
			int nHit 		 = dao.newsHit(news_no);
			News_DTO dto 	 = dao.getNewsView(news_no);
			request.setAttribute("t_dto", dto);
			RequestDispatcher rdp = request.getRequestDispatcher("/news/news_view.jsp");
			rdp.forward(request, response);
		}else if(gubun.equals("save")) {
			News_DAO dao = new News_DAO();
			String news_no  = dao.getNewsNo();
			String title 	= request.getParameter("t_title");	
			String content 	= request.getParameter("t_content");
			String reg_id 	= request.getParameter("t_reg_name");
			String reg_date = CommonUtil.getToday();
			int hit 		= 0;
			
			News_DTO news_dto = new News_DTO(news_no, title, content, reg_id, reg_date, hit);
			int result = dao.insertNews(news_dto);
			
			RequestDispatcher dispatcher = null;
			String msg="";
			String url="";
			
			if(result > 0) {
				msg = "등록되었습니다.";
				url = "/track2_webprogram_3차_임수혁/NewsList_Exam";
			} else {
				msg = "등록 실패.";
				url = "/track2_webprogram_3차_임수혁/NewsList_Exam";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
			dispatcher.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}