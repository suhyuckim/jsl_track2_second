package jsl.spr.mypjt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import command.member.CommandMember;
import command.member.CommandMemberDelete;
import command.member.CommandMemberIDCheck;
import command.member.CommandMemberList;
import command.member.CommandMemberSave;
import command.member.CommandMemberUpdate;
import command.member.CommandMemberView;
import dao.Member_dao;
import dto.Member_dto;

@Controller
public class MemberController {
	
//  ========== 인터페이스 ==========
	@RequestMapping("/MemberList") //조회
	public String memberList(HttpServletRequest request, Model model) {
		//CommandMemberList mem = new CommandMemberList();
		CommandMember mem = new CommandMemberList();
		mem.execute(request, model);
		return "/member/member_list";
	}
	
	@RequestMapping("/MemberView") //상세보기
	public String memberView(HttpServletRequest request, Model model) {
		//CommandMemberView mem = new CommandMemberView();
		CommandMember mem = new CommandMemberView(); //인터페이스
		mem.execute(request, model);
		return "/member/member_view";
	}
	
	@RequestMapping("/MemberSave") //맴버 등록
	public String memberSave(HttpServletRequest request, Model model) {
		CommandMember mem = new CommandMemberSave();
		mem.execute(request, model);
		return "redirect:/MemberList";
	}
	
	@RequestMapping("/MemberWriteForm") //등록페이지로 이동
	public String memberWriteForm() {
		return "/member/member_write";
	}
	
	@RequestMapping("/MemberIdCheck") //id중복검사 및 AJAX
	public void memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		CommandMemberIDCheck check = new CommandMemberIDCheck();
		check.execute(request, response);
	}
	
	@RequestMapping("/MemberUpdateForm") //수정페이지로 이동
	public String memberUpdateForm(HttpServletRequest request, Model model) {
		CommandMember mem = new CommandMemberView(); //인터페이스
		mem.execute(request, model);
		return "/member/member_update";
	}
	
	@RequestMapping("/MemberUpdate") //수정저장
	public String memberUpdate(HttpServletRequest request, Model model) {
		CommandMember mem = new CommandMemberUpdate();
		mem.execute(request, model);
		return "redirect:/MemberList";
	}
	
	@RequestMapping("/MemberDelete") //삭제
	public String memberDelete(HttpServletRequest request, Model model) {
		CommandMember mem = new CommandMemberDelete();
		mem.execute(request, model);
		return "redirect:/MemberList";
	}
//  ========== 인터페이스 ==========	
	
	
/*
	@RequestMapping("/MemberDelete") //삭제
	public String memberDelete(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		int result = dao.deleteMember(id);
		if(result == 0) System.out.println("삭제 실패하였습니다.");
		return "redirect:/MemberList";
	}
	
	@RequestMapping("/MemberUpdate") //수정저장
	public String memberUpdate(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String area = request.getParameter("t_area");
		String age = request.getParameter("t_age");
		Member_dto dto = new Member_dto(id, name, area, Integer.parseInt(age));
		int result = dao.updateMember(dto);
		if(result == 0) System.out.println("수정을 실패하였습니다.");
		return "redirect:/MemberList";
	}
	
	@RequestMapping("/MemberUpdateForm") //수정페이지로 이동
		public String memberUpdateForm(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		Member_dto dto = dao.getMemberView(id);
		model.addAttribute("t_dto", dto);
		return "/member/member_update";
	}
	
	@RequestMapping("/MemberView") //상세보기
		public String memberView(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		Member_dto dto = dao.getMemberView(id);
		model.addAttribute("t_dto", dto);
		return "/member/member_view";
	}
	
	@RequestMapping("/MemberIdCheck") //id중복검사 및 AJAX
		public void memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		String result = dao.getCheck(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(result);
	}
	
	@RequestMapping("/MemberSave") //맴버 등록
		public String memberSave(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String id 	= request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String area = request.getParameter("t_area");
		String age  = request.getParameter("t_age");
		Member_dto dto = new Member_dto(id, name, area, Integer.parseInt(age));
		
		int result = dao.saveMember(dto);
		if(result == 0) System.out.println("등록 실패하였습니다.");
		return "redirect:/MemberList"; //컨트롤러중에 이렇게 @RequestMapping을 실행해라
	}
	
	@RequestMapping("/MemberWriteForm") //등록페이지로 이동
		public String memberWriteForm() {
			return "/member/member_write";
		}

	@RequestMapping("/MemberList") //조회
	public String memberList(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(select == null) {
			select = "name";
			search = "";
		}
		ArrayList<Member_dto> dtos = dao.getMemberList(select, search);
		model.addAttribute("t_dtos", dtos);
		model.addAttribute("t_select", select);
		model.addAttribute("t_search", search);
		return "/member/member_list";
	}
*/	
}