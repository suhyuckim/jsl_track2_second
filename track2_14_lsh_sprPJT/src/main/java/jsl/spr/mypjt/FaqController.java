package jsl.spr.mypjt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import command.faq.CommandFaq;
import command.faq.CommandFaqDelete;
import command.faq.CommandFaqList;
import command.faq.CommandFaqSave;
import command.faq.CommandFaqUpdate;
import command.faq.CommandFaqView;
import command.news.CommandNews;

@Controller
public class FaqController {
	@RequestMapping("/Faq")
	public String faq(HttpServletRequest request, Model model) { 
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage = "";
		if(gubun.equals("list")){ //���
			CommandFaq faq = new CommandFaqList();
			faq.execute(request, model);
			viewPage = "/faq/faq_list";
		} else if (gubun.equals("writeForm")) { //����������� �̵�
			viewPage = "/faq/faq_write";
		} else if(gubun.equals("save")) { //���
			CommandFaq faq = new CommandFaqSave();
			faq.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("view")) { //�󼼺���
			CommandFaq faq = new CommandFaqView();
			faq.execute(request, model);
			viewPage = "/faq/faq_view";
		} else if(gubun.equals("updateForm")) { //������������ �̵�
			CommandFaq faq = new CommandFaqView();
			faq.execute(request, model);
			viewPage = "/faq/faq_update";
		} else if(gubun.equals("update")) { //����
			CommandFaq faq = new CommandFaqUpdate();
			faq.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("delete")) { //����
			CommandFaq faq = new CommandFaqDelete();
			faq.execute(request, model);
			viewPage = "/common_alert_page";
		}
		return viewPage;
	}
}