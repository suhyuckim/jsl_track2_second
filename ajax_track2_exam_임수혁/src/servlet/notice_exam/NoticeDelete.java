package servlet.notice_exam;

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
import dao.Notice_DAO;

/**
 * Servlet implementation class NoticeDelete
 */
@WebServlet("/NoticeDelete")
public class NoticeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
			String msg= "";
			String url= "";
			Notice_DAO dao 	 = new Notice_DAO();	
			String notice_no = request.getParameter("t_notice_no");	
			String fileName  = request.getParameter("t_fileName");
			String file_dir  = CommonUtil.file_dir_notice;
			int result 		 = dao.deleteNotice(notice_no);	
			if(fileName != null){
				File dFa = new File(file_dir, fileName);
				dFa.delete();
			}
			
			if(result > 0) {
				msg = "삭제되었습니다.";
				url = "/NoticeList";
			} else {
				msg = "삭제 실패.";
				url = "/NoticeList";
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
