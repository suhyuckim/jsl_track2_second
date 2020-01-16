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

import com.oreilly.servlet.MultipartRequest;

import common.CommonUtil;
import dao.News_DAO;
import dto.News_DTO;

/**
 * Servlet implementation class NewsSave_Servlet
 */
@WebServlet("/NewsSave_Servlet")
public class NewsSave_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsSave_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		String session_level = (String)session.getAttribute("session_level");
		RequestDispatcher dispatcher = null;
		String msg = "";
		String url = "";
		
		if(session_level == null) {
			msg="로그인 정보가 만료되었습니다.";
			url="/Index";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		} else {
			News_DAO dao = new News_DAO();
			String news_no = dao.getNewsNo();
			
			int sizeLimit 		= 1024*1024*5;
			String file_dir     = CommonUtil.file_dir_news;
			MultipartRequest mpr = new MultipartRequest(request, file_dir, sizeLimit, "UTF-8");
			
			String fileName 	= mpr.getFilesystemName("fileName_a");
			String saveFileName = "";
			if(fileName != null){
				File oldFile = new File(file_dir, fileName);
				File newFile = new File(file_dir, news_no+"-"+fileName);
				oldFile.renameTo(newFile);
				saveFileName = newFile.getName();
			} 
			String title 	= mpr.getParameter("title");	
			String content 	= mpr.getParameter("contents");
			String reg_id 	= mpr.getParameter("writer");	
			String reg_date = CommonUtil.getToday();
			int hit 		= 0;
			
			News_DTO news_dto = new News_DTO(news_no, title, content, reg_id, reg_date, hit, saveFileName);
			int result = dao.insertNews_servlet(news_dto);
			
			if(result > 0) {
				msg = "등록되었습니다.";
				url = "/NewList_Servlet";
			} else {
				msg = "등록 실패.";
				url = "/NewList_Servlet";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		}
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
