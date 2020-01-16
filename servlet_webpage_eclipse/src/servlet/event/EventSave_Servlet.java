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
import dao.Notice_DAO;
import dto.Event_DTO;
import dto.Notice_DTO;

/**
 * Servlet implementation class EventSave_Servlet
 */
@WebServlet("/EventSave_Servlet")
public class EventSave_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventSave_Servlet() {
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
			Event_DAO dao = new Event_DAO();
			String event_no = dao.getEventNo();
			
			int sizeLimit 		= 1024*1024*5;
			String file_dir     = CommonUtil.file_dir_event;
			MultipartRequest mpr = new MultipartRequest(request, file_dir, sizeLimit, "UTF-8");
			
			String fileName 	= mpr.getFilesystemName("fileName_a");
			String saveFileName = "";
			if(fileName != null){
				File oldFile = new File(file_dir, fileName);
				File newFile = new File(file_dir, event_no+"-"+fileName);
				oldFile.renameTo(newFile);
				saveFileName = newFile.getName();
			} 
			String title 	= mpr.getParameter("t_title");	
			String content 	= mpr.getParameter("contents");
			String reg_date = CommonUtil.getToday();
			String reg_start= mpr.getParameter("start_date");
			String reg_end 	= mpr.getParameter("end_date");
			String reg_id 	= mpr.getParameter("writer");	
			int hit 		= 0;
			
			Event_DTO event_dto = new Event_DTO(event_no, title, content, reg_id, reg_date, reg_start, reg_end, saveFileName, hit);
			int result = dao.insertEvent_servlet(event_dto);
			
			if(result > 0) {
				msg = "등록되었습니다.";
				url = "/EventList_Servlet";
			} else {
				msg = "등록 실패.";
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
