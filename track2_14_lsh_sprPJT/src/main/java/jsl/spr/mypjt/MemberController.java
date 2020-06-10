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
	
//  ========== �������̽� ==========
	@RequestMapping("/MemberList") //��ȸ
	public String memberList(HttpServletRequest request, Model model) {
		//CommandMemberList mem = new CommandMemberList();
		CommandMember mem = new CommandMemberList();
		mem.execute(request, model);
		return "/member/member_list";
	}
	
	@RequestMapping("/MemberView") //�󼼺���
	public String memberView(HttpServletRequest request, Model model) {
		//CommandMemberView mem = new CommandMemberView();
		CommandMember mem = new CommandMemberView(); //�������̽�
		mem.execute(request, model);
		return "/member/member_view";
	}
	
	@RequestMapping("/MemberSave") //�ɹ� ���
	public String memberSave(HttpServletRequest request, Model model) {
		CommandMember mem = new CommandMemberSave();
		mem.execute(request, model);
		return "redirect:/MemberList";
	}
	
	@RequestMapping("/MemberWriteForm") //����������� �̵�
	public String memberWriteForm() {
		return "/member/member_write";
	}
	
	@RequestMapping("/MemberIdCheck") //id�ߺ��˻� �� AJAX
	public void memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		CommandMemberIDCheck check = new CommandMemberIDCheck();
		check.execute(request, response);
	}
	
	@RequestMapping("/MemberUpdateForm") //������������ �̵�
	public String memberUpdateForm(HttpServletRequest request, Model model) {
		CommandMember mem = new CommandMemberView(); //�������̽�
		mem.execute(request, model);
		return "/member/member_update";
	}
	
	@RequestMapping("/MemberUpdate") //��������
	public String memberUpdate(HttpServletRequest request, Model model) {
		CommandMember mem = new CommandMemberUpdate();
		mem.execute(request, model);
		return "redirect:/MemberList";
	}
	
	@RequestMapping("/MemberDelete") //����
	public String memberDelete(HttpServletRequest request, Model model) {
		CommandMember mem = new CommandMemberDelete();
		mem.execute(request, model);
		return "redirect:/MemberList";
	}
//  ========== �������̽� ==========	
	
	
/*
	@RequestMapping("/MemberDelete") //����
	public String memberDelete(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		int result = dao.deleteMember(id);
		if(result == 0) System.out.println("���� �����Ͽ����ϴ�.");
		return "redirect:/MemberList";
	}
	
	@RequestMapping("/MemberUpdate") //��������
	public String memberUpdate(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String area = request.getParameter("t_area");
		String age = request.getParameter("t_age");
		Member_dto dto = new Member_dto(id, name, area, Integer.parseInt(age));
		int result = dao.updateMember(dto);
		if(result == 0) System.out.println("������ �����Ͽ����ϴ�.");
		return "redirect:/MemberList";
	}
	
	@RequestMapping("/MemberUpdateForm") //������������ �̵�
		public String memberUpdateForm(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		Member_dto dto = dao.getMemberView(id);
		model.addAttribute("t_dto", dto);
		return "/member/member_update";
	}
	
	@RequestMapping("/MemberView") //�󼼺���
		public String memberView(HttpServletRequest request, Model model) {
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		Member_dto dto = dao.getMemberView(id);
		model.addAttribute("t_dto", dto);
		return "/member/member_view";
	}
	
	@RequestMapping("/MemberIdCheck") //id�ߺ��˻� �� AJAX
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
	
	@RequestMapping("/MemberSave") //�ɹ� ���
		public String memberSave(HttpServletRequest request) {
		Member_dao dao = new Member_dao();
		String id 	= request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String area = request.getParameter("t_area");
		String age  = request.getParameter("t_age");
		Member_dto dto = new Member_dto(id, name, area, Integer.parseInt(age));
		
		int result = dao.saveMember(dto);
		if(result == 0) System.out.println("��� �����Ͽ����ϴ�.");
		return "redirect:/MemberList"; //��Ʈ�ѷ��߿� �̷��� @RequestMapping�� �����ض�
	}
	
	@RequestMapping("/MemberWriteForm") //����������� �̵�
		public String memberWriteForm() {
			return "/member/member_write";
		}

	@RequestMapping("/MemberList") //��ȸ
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