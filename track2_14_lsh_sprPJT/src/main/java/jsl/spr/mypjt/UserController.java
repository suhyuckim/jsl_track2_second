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
		if(gubun.equals("loginform")) { //�α����������� �̵�
			viewPage = "/user/user_login";
		} else if(gubun.equals("join")) { //ȸ�������������� �̵�
			viewPage = "/user/user_join";
		} else if(gubun.equals("userSave")) { //ȸ������
			CommandUser user = new UserSave();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("userLogin")) { //�α���
			CommandUser user = new UserLogin();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("userLogout")) { //�α׾ƿ�
			CommandUser user = new UserLogout();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("view")) { //������������ �̵�
			CommandUser user = new UserView();
			user.execute(request, model);
			String msgGubun = (String)request.getAttribute("t_msg");
			if(msgGubun.equals("")) viewPage = "/user/user_view";
			else viewPage = "/common_alert_page";
		} else if(gubun.equals("updateForm")) { //���������������� �̵�
			CommandUser user = new UserView();
			user.execute(request, model);
			String msgGubun = (String)request.getAttribute("t_msg");
			if(msgGubun.equals("")) viewPage = "/user/user_update";
			else viewPage = "/common_alert_page";
		} else if(gubun.equals("userUpdate")) { //��������
			CommandUser user = new UserUpdate();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("userExit")) { //ȸ��Ż��
			CommandUser user = new UserExit();
			user.execute(request, model);
			viewPage = "/common_alert_page";
		}
		return viewPage;
	}
	@RequestMapping("/UserIdCheck") //id�ߺ��˻�
	public void userIdCheck(HttpServletRequest request, HttpServletResponse response) {
		UserIdCheck user = new UserIdCheck();
		user.execute(request, response);
	}
}