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
 * Servlet implementation class MemberJoin
 */
@WebServlet("/MemberJoin")
public class MemberJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberJoin() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member_DAO dao = new Member_DAO();
		
		RequestDispatcher dispatcher = null;
		String msg = "";
		String url = "";
		
		String id 		= request.getParameter("id");
		String pw 		= request.getParameter("pw");
		String s_pw = "";
		try {
			s_pw = CommonUtil.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name 	= request.getParameter("name");	
		String telecom  = request.getParameter("telephone");
		String phone_1 	= request.getParameter("phone_1");
		String phone_2  = request.getParameter("phone_2");
		String phone_3  = request.getParameter("phone_3");
		String area 	= request.getParameter("area");
		String address  = request.getParameter("address");
		String sex  	= request.getParameter("sex");
		String birth 	= request.getParameter("birth");
		String email_1  = request.getParameter("email1");
		String email_2  = request.getParameter("email2");
		String reg_date = CommonUtil.getToday();
	
		int result = dao.insertMember_servlet(id,s_pw,name,birth,area,address,telecom, phone_1,phone_2,phone_3,
									          email_1,email_2,reg_date,sex);
		
		if(result > 0) {
			msg = "회원가입 처리되었습니다.";
			url = "/MemberLoginForm";
		} else {
			msg = "회원가입 처리되지 못했습니다.";
			url = "/Index";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		dispatcher = request.getRequestDispatcher("/common/view_messageAlert.jsp");
		dispatcher.forward(request, response);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}