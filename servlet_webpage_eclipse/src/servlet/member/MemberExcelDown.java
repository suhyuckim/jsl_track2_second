package servlet.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Member_DAO;
import dto.Member_DTO;

/**
 * Servlet implementation class MemberExcelDown
 */
@WebServlet("/MemberExcelDown")
public class MemberExcelDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberExcelDown() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member_DAO dao   = new Member_DAO();
		String selValue   = request.getParameter("t_sel"); 
		String selValue2  = request.getParameter("t_sel2");
		String txtValue   = CommonUtil.checkNull(request.getParameter("t_search"));
		String checkValue = request.getParameter("t_check");
		if(selValue == null){
			selValue  = "all";		
			txtValue  = "";
			selValue2 = "";
		}	
		if(checkValue == null){
			checkValue = "reg_date";
		}
			ArrayList<Member_DTO> dtos = dao.getListMember_servlet(selValue, selValue2, txtValue, checkValue);
			request.setAttribute("t_dtos", dtos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/common/excel_down_member.jsp");
			dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}