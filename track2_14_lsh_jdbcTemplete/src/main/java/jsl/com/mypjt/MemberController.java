package jsl.com.mypjt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import command.member.MemberLogin;
import common.CommonTemplate;

@Controller
public class MemberController {
	@Autowired
	public JdbcTemplate template;
	
	@Autowired
	public void setTemp1() {
		CommonTemplate.setTempl(template);
	}
	
	@RequestMapping("/MemberLogout")
	public String memberLogout(HttpServletRequest request) { //로그아웃
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/Member")
	public String member(HttpServletRequest request, Model model) {
		String gubun = request.getParameter("t_gubun");
		String viewPage="";
		if(gubun == null) gubun="loginForm";
		if(gubun.equals("loginForm")) { //로그인 페이지
			viewPage = "/member/member_login";
		} else if(gubun.equals("login")) { //로그인
			MemberLogin mem = new MemberLogin();
			mem.execute(request, model);
			viewPage = "/common_alert_page";
		}
		return viewPage;
	}
}