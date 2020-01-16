package servlet.member;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Member_DAO;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/MemberUpdate")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member_DAO dao 		= new Member_DAO();
		String id 			= request.getParameter("id");
		String pw 			= request.getParameter("re_pw");
		String s_pw = "";
		try {
			s_pw = CommonUtil.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String name = dao.checkLogin_servlet(id, s_pw);
		
		String msg  = "";
		String url  = "";
		RequestDispatcher rdp = null;
		if(name == null) {
			msg = "비밀번호 오류입니다. 다시 입력해 주세요";
			url = "/MemberMyinfo";	
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		} else {
			String birth 		= request.getParameter("birth");
			String area 		= request.getParameter("area");
			String address  	= request.getParameter("address");
			String telecom  	= request.getParameter("telecom");
			String phone_1 		= request.getParameter("phone_1");
			String phone_2  	= request.getParameter("phone_2");
			String phone_3  	= request.getParameter("phone_3");
			String email1  		= request.getParameter("email1");
			String email2  		= request.getParameter("email2");
			String sex		  	= request.getParameter("sex");
			String update_date  = CommonUtil.getToday();
						
			int result = dao.updateMember_servlet(id,name,birth,area,address,telecom, phone_1,phone_2,phone_3,
												  email1,email2,update_date,sex);
			if(result > 0) {
				msg = "회원정보 수정되었습니다.";
				url = "/Index";
			} else {
				msg = "회원정보 처리되지 못하였습니다.";
				url = "/Index";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			rdp = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		}
		rdp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
