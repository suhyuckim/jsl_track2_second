package controller_news;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.CommonUtil;
import dao.News_DAO;
import dto.News_DTO;

/**
 * Servlet implementation class NewsSave_Exam
 */
@WebServlet("/NewsSave_Exam")
public class NewsSave_Exam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsSave_Exam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
