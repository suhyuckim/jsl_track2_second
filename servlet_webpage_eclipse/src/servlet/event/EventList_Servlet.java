package servlet.event;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.CommonUtil;
import dao.Event_DAO;
import dto.Event_DTO;

/**
 * Servlet implementation class EventList_Servlet
 */
@WebServlet("/EventList_Servlet")
public class EventList_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventList_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Event_DAO dao = new Event_DAO();
		String selValue = request.getParameter("t_sel"); 
		String txtValue = request.getParameter("t_search");
		if(selValue == null){
			selValue = "all";
			txtValue = "";
		}
		if(txtValue == null){
			txtValue = "";
		}
		ArrayList<Event_DTO> dtos = dao.getEventList_servlet(selValue, txtValue);
		int EventCount = dao.getEventCount();
		request.setAttribute("EventCount", EventCount);
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("selValue", selValue);
		request.setAttribute("txtValue", txtValue);

		//************* page 시작 *************/
		String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
		int			current_page;					// 현재페이지 번호
		int			total_page;						// 총페이지 수
		int			total_count;					// 총레코드 수
		int			for_count;						
		int			list_setup_count = 1;			// 한번에 출력될 List 수, 한페이지당 몇줄 보여줄것인지
		int			p_no;
		int			v_count;
		int			a_count;
		String		url				= null;	

		// 조회된 건수 구하기  total_count : 설정
		if(dtos == null) total_count =0; 			//게시판 테이블에 있는 전체 숫자
		else total_count = dtos.size(); 

		// 페이지번호가 없으면 1페이지로 간주
		if(r_page.equals("")) current_page = 1;
		else current_page = Integer.parseInt(r_page);
			
		for_count		= list_setup_count;
		p_no			= list_setup_count;				// 페이지수가 10
		total_page = total_count / list_setup_count;	// 전체페이지수 계산 (if 23개 게시물이면 2)
	   
		if(current_page == 1) {
			v_count		= 0;
			a_count		= list_setup_count;
			for_count	= 0;
		} else{
			v_count		= 0;
			a_count		= p_no * current_page;
			for_count	= a_count - list_setup_count;
		}
		if(total_page * list_setup_count != total_count)   total_page = total_page +1;
		//************* page 끝 *************/		

		request.setAttribute("total_page", total_page);
		request.setAttribute("current_page", current_page);
		request.setAttribute("for_count", for_count);
		request.setAttribute("v_count", v_count);
		request.setAttribute("a_count", a_count);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/event/event_list.jsp");
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
