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

/**
 * Servlet implementation class NoticeUpdate
 */
@WebServlet("/NoticeUpdate")
public class NoticeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeUpdate() {
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
			
			int sizeLimit 		 = 1024*1024*5;
			String file_dir  	 = CommonUtil.file_dir_notice; 
			MultipartRequest mpr = new MultipartRequest(request, file_dir, sizeLimit, "UTF-8");
			
			String notice_no 	= mpr.getParameter("t_notice_no");	
			String title 		= mpr.getParameter("title");	
			String content 		= mpr.getParameter("cont");	
			String reg_id 		= "manager";	
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
				File newFile = new File(file_dir, notice_no+"-"+fileName);
				oldFile.renameTo(newFile);
				saveFileName = newFile.getName();
			}	
				int result = dao.updateNotice(notice_no, title, content, saveFileName, reg_id, reg_date);
				
				if(result > 0) {
					msg = "수정되었습니다.";
					url = "/NoticeList";
				} else {
					msg = "수정 실패.";
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