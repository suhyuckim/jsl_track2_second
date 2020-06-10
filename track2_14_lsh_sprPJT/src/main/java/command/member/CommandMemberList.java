package command.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import common.CommonUtil;
import dao.Member_dao;
import dto.Member_dto;

public class CommandMemberList implements CommandMember {
	@Override
	public void execute(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(select == null) {
			select = "name";
			search = "";
		}
//		ArrayList<Member_dto> dtos = dao.getMemberList(select, search);
		
		ArrayList<Member_dto> dtos = new ArrayList<Member_dto>();
		Member_dto dto1 = new Member_dto("101","USER1","대전",26);
		Member_dto dto2 = new Member_dto("102","USER2","대전",26);
		Member_dto dto3 = new Member_dto("103","USER3","대전",26);
		Member_dto dto4 = new Member_dto("104","USER4","대전",26);
		Member_dto dto5 = new Member_dto("105","USER5","대전",26);
		Member_dto dto6 = new Member_dto("106","USER6","대전",26);
		Member_dto dto7 = new Member_dto("107","USER7","대전",26);
		Member_dto dto8 = new Member_dto("108","USER8","대전",26);
		Member_dto dto9 = new Member_dto("109","USER9","대전",26);
		Member_dto dto10 = new Member_dto("110","USER10","대전",26);
		Member_dto dto11 = new Member_dto("111","USER11","대전",26);
		Member_dto dto12 = new Member_dto("112","USER12","대전",26);
		Member_dto dto13 = new Member_dto("113","USER13","대전",26);
		dtos.add(dto1);
		dtos.add(dto2);
		dtos.add(dto3);
		dtos.add(dto4);
		dtos.add(dto5);
		dtos.add(dto6);
		dtos.add(dto7);
		dtos.add(dto8);
		dtos.add(dto9);
		dtos.add(dto10);
		dtos.add(dto11);
		dtos.add(dto12);
		dtos.add(dto13);
		
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