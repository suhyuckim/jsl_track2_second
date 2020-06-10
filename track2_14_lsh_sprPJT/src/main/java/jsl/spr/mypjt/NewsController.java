package jsl.spr.mypjt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import command.news.CommandNews;
import command.news.NewsDelete;
import command.news.NewsList;
import command.news.NewsSave;
import command.news.NewsUpdate;
import command.news.NewsView;

@Controller
public class NewsController {
	@RequestMapping("/News")
	public String news(HttpServletRequest request, Model model) {
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage = "";
		if(gubun.equals("list")){ //���
//			NewsList news = new NewsList();
			CommandNews news = new NewsList();
			news.execute(request, model);
			viewPage = "/news/news_list";
		} else if(gubun.equals("writeForm")) { //����������� �̵�
			viewPage = "/news/news_write";
		} else if(gubun.equals("save")) { //���
			CommandNews news = new NewsSave();
			news.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("view")) { //�󼼺���
			CommandNews news = new NewsView();
			news.execute(request, model);
			viewPage = "/news/news_view";
		} else if(gubun.equals("updateForm")) { //������������ �̵�
			CommandNews news = new NewsView();
			news.execute(request, model);
			viewPage = "/news/news_update";
		} else if(gubun.equals("update")) { //����
			CommandNews news = new NewsUpdate();
			news.execute(request, model);
			viewPage = "/common_alert_page";
		} else if(gubun.equals("delete")) { //����
			CommandNews news = new NewsDelete();
			news.execute(request, model);
			viewPage = "/common_alert_page";
		}
		return viewPage;
	}
//	@RequestMapping("/NewsWriteForm")
//	public String newsWriteForm() {
//		return "/news/news_write";
//	}
}