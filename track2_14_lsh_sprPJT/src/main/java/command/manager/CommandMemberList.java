package command.manager;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import common.CommonUtil;
import dao.User_dao;
import dto.User_dto;

public class CommandMemberList implements CommandManager {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		User_dao dao = new User_dao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(select == null) {
			select = "name";
			search = "";
		}
		ArrayList<User_dto> dtos = dao.getMemberList(select, search);

		model.addAttribute("t_dtos", dtos);
		model.addAttribute("t_select", select);
		model.addAttribute("t_search", search);
		
		//******** page 시작*********/
		String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
		int			current_page;					// 현재페이지 번호
		int			total_page;						// 총페이지 수
		int			total_count;					// 총레코드 수
		int			for_count;
				
		//***************************************//
		int			list_setup_count = 3;			// 한번에 출력될 List 수
		//***************************************//
		int			p_no;
		int			v_count;
		int			a_count;
		String		url				= null;	
		// 조회된 건수 구하기  total_count : 설정
		if(dtos == null) total_count=0;
		else total_count = dtos.size(); 

		// 페이지번호가 없으면 1페이지로 간주
		if(r_page.equals("")) current_page = 1;
		else current_page = Integer.parseInt(r_page);
		for_count		= list_setup_count;
		p_no			= list_setup_count;				// 페이지수가 10
		total_page = total_count / list_setup_count;		// 전체페이지수 계산 (if 23개 게시물이면 2)
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
		//******** page 끝*********/	
							
		model.addAttribute("v_count", v_count);
		model.addAttribute("for_count", for_count);
		model.addAttribute("a_count", a_count);
		model.addAttribute("current_page", current_page);
		model.addAttribute("total_page", total_page);
	}
}