package servlet.notice;

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
import dao.Notice_DAO;
import dto.Notice_DTO;

/**
 * Servlet implementation class NoticeSave
 */
@WebServlet("/NoticeSave")
public class NoticeSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeSave() {
        super();
    }
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
			Notice_DAO dao = new Notice_DAO();
			String notice_no = dao.getNoticeNo();
			
			int sizeLimit 		= 1024*1024*5;
			String file_dir     = CommonUtil.file_dir_notice;
			MultipartRequest mpr = new MultipartRequest(request, file_dir, sizeLimit, "UTF-8");
			
			String fileName 	= mpr.getFilesystemName("fileName_a");
			String saveFileName = "";
			if(fileName != null){
				File oldFile = new File(file_dir, fileName);
				File newFile = new File(file_dir, notice_no+"-"+fileName);
				oldFile.renameTo(newFile);
				saveFileName = newFile.getName();
			} 
			String title 	= mpr.getParameter("title");	
			String content 	= mpr.getParameter("cont");
			String reg_id 	= "manager";	
			String reg_date = CommonUtil.getToday();
			int hit 		= 0;
			
			Notice_DTO notice_dto = new Notice_DTO(notice_no, title, content, saveFileName, reg_id, reg_date, hit);
			int result = dao.insertNotice(notice_dto);
			
			if(result > 0) {
				msg = "등록되었습니다.";
				url = "/NoticeList";
			} else {
				msg = "등록 실패.";
				url = "/NoticeList";
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