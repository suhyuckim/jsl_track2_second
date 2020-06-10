package jsl.spr.mypjt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import command.manager.CommandManager;
import command.manager.CommandMemberList;
import command.manager.CommandMemberView;

@Controller
public class ManagerController {
	@RequestMapping("/Manager")
	public String faq(HttpServletRequest request, Model model) { 
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage = "";
		if(gubun.equals("list")){ //목록
			CommandManager manager = new CommandMemberList();
			manager.execute(request, model);
			viewPage = "/manager/manager_list";
		} else if(gubun.equals("view")) { //상세보기
			CommandManager manager = new CommandMemberView();
			manager.execute(request, model);
			viewPage = "/manager/manager_view";
		}
		return viewPage;
	}
}