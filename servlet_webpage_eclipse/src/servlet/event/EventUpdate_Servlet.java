package servlet.event;

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
import dao.Event_DAO;

/**
 * Servlet implementation class EventUpdate_Servlet
 */
@WebServlet("/EventUpdate_Servlet")
public class EventUpdate_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventUpdate_Servlet() {
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
			Event_DAO dao 		 = new Event_DAO();
			int sizeLimit 		 = 1024*1024*5;
			String file_dir  	 = CommonUtil.file_dir_event; 
			MultipartRequest mpr = new MultipartRequest(request, file_dir, sizeLimit, "UTF-8");
			String event_no 	= mpr.getParameter("t_event_no");	
			String title 		= mpr.getParameter("t_title");	
			String content 		= mpr.getParameter("contents");	
			String reg_id 		= mpr.getParameter("writer");		
			String reg_date 	= CommonUtil.getToday();
			String start_date 	= mpr.getParameter("start_date");
			String end_date 	= mpr.getParameter("end_date");	
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
				File newFile = new File(file_dir, event_no+"-"+fileName);
				oldFile.renameTo(newFile);
				saveFileName = newFile.getName();
			}
			int result = dao.updateEvent_servlet(event_no, title, content, reg_id, reg_date, start_date, end_date, saveFileName);
			
			if(result > 0) {
				msg = "수정되었습니다.";
				url = "/EventList_Servlet";
			} else {
				msg = "수정 실패.";
				url = "/EventList_Servlet";
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
