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

/**
 * Servlet implementation class NewsUpdate_Servlet
 */
@WebServlet("/NewsUpdate_Servlet")
public class NewsUpdate_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsUpdate_Servlet() {
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
			int sizeLimit 		 = 1024*1024*5;
			String file_dir  	 = CommonUtil.file_dir_news; 
			MultipartRequest mpr = new MultipartRequest(request, file_dir, sizeLimit, "UTF-8");
			String news_no 		= mpr.getParameter("t_news_no");	
			String title 		= mpr.getParameter("title");	
			String content 		= mpr.getParameter("contents");	
			String reg_id 		= mpr.getParameter("writer");		
			String reg_date 	= CommonUtil.getToday();
			String saveFileName = "";
			String fileName 	= mpr.getFilesystemName("fileName_a"); 
			String delFile 		= CommonUtil.checkNull(mpr.getParameter("checkBox_del_fileName"));  //삭제시킬 파일명
			if(!delFile.equals("")){ //checkbox에 삭제할 파일이 있다
				File dFa = new File(file_dir, delFile);
				dFa.delete();
			} else {
				saveFileName   = mpr.getParameter("ori_fileName_a");
			}
			if(fileName != null){
				String delFile_1 = mpr.getParameter("ori_fileName_a");
				if(delFile_1 != null){
					File dFa = new File(file_dir, delFile_1);
					dFa.delete();
				}
				File oldFile = new File(file_dir, fileName);
				File newFile = new File(file_dir, news_no+"-"+fileName);
				oldFile.renameTo(newFile);
				saveFileName = newFile.getName();
			}
			int result = dao.updateNews_servlet(news_no, title, content, saveFileName, reg_id, reg_date);
			
			if(result > 0) {
				msg = "수정되었습니다.";
				url = "/NewList_Servlet";
			} else {
				msg = "수정 실패.";
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