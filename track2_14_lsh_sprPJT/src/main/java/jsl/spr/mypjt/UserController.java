package jsl.spr.mypjt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import command.user.CommandUser;
import command.user.UserExit;
import command.user.UserIdCheck;
import command.user.UserLogout;
import command.user.UserLogin;
import command.user.UserSave;
import command.user.UserUpdate;
import command.user.UserView;

@Controller
public class UserController {
	@RequestMapping("/User")
	public String user(HttpServletRequest request, Model model) {
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "loginform";
		String viewPage = "";
		if(gubun.equals("loginform")) { //로그인페이지로 이동
			viewPage = "/user/user_login";
		} else if(gubun.equals("join")) { //회원가입페이지로 이동
			viewPage = "/user/user_join";
		} else if(gubun.equals("userSave")) { //회원가입
			CommandUser user = new UserSave();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("userLogin")) { //로그인
			CommandUser user = new UserLogin();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("userLogout")) { //로그아웃
			CommandUser user = new UserLogout();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("view")) { //마이페이지로 이동
			CommandUser user = new UserView();
			user.execute(request, model);
			String msgGubun = (String)request.getAttribute("t_msg");
			if(msgGubun.equals("")) viewPage = "/user/user_view";
			else viewPage = "/common_alert_page";
		} else if(gubun.equals("updateForm")) { //정보수정페이지로 이동
			CommandUser user = new UserView();
			user.execute(request, model);
			String msgGubun = (String)request.getAttribute("t_msg");
			if(msgGubun.equals("")) viewPage = "/user/user_update";
			else viewPage = "/common_alert_page";
		} else if(gubun.equals("userUpdate")) { //정보수정
			CommandUser user = new UserUpdate();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("userExit")) { //회원탈퇴
			CommandUser user = new UserExit();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		}
		return viewPage;
	}
	@RequestMapping("/UserIdCheck") //id중복검사
	public void userIdCheck(HttpServletRequest request, HttpServletResponse response) {
		UserIdCheck user = new UserIdCheck();
		user.execute(request, response);
	}
}