package jsl.com.mypjt;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import command.news.CommandNews;
import command.news.NewsDelete;
import command.news.NewsList;
import command.news.NewsSave;
import command.news.NewsUpdate;
import command.news.NewsView;
import common.CommonTemplate;
import common.CommonUtil;
import dto.News_dto;

@Controller
public class NewsController {
	@Autowired
	public JdbcTemplate template;
	
	@Autowired
	public void setTemp1() {
		CommonTemplate.setTempl(template);
	}
	
	@RequestMapping("/News")
	public String news(HttpServletRequest request, Model model) {
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage = "";
		if(gubun.equals("list")) { //목록
			
			CommandNews news = new NewsList();
			news.execute(request, model);
//			news.execute(request, model, template);
//			String select = request.getParameter("t_select");
//			String search = request.getParameter("t_search");
//			if(select == null) {
//				select = "title";
//				search = "";
//			}
//			RowMapper<News_dto> News_dtoS = new BeanPropertyRowMapper<News_dto>(News_dto.class);	
//			String query = " select a.no, a.title, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
//						   " from track2_14_news a, track2_14_member b "+ 
//						   " where a.reg_info = b.id "+
//						   " and "+select+" like '%"+search+"%' "+
//					       " order by no desc ";
//			ArrayList<News_dto> dtos = (ArrayList<News_dto>)template.query(query, News_dtoS);
//			model.addAttribute("t_dtos",dtos);
//			model.addAttribute("t_select",select);
//			model.addAttribute("t_search",search);
//			
//			//******** page 시작*********/
//			String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
//			int			current_page;					// 현재페이지 번호
//			int			total_page;						// 총페이지 수
//			int			total_count;					// 총레코드 수
//			int			for_count;
//					
//			//***************************************//
//			int			list_setup_count = 3;			// 한번에 출력될 List 수
//			//***************************************//
//			int			p_no;
//			int			v_count;
//			int			a_count;
//			String		url				= null;	
//			// 조회된 건수 구하기  total_count : 설정
//			if(dtos == null) total_count=0;
//			else total_count = dtos.size(); 
//
//			// 페이지번호가 없으면 1페이지로 간주
//			if(r_page.equals("")) current_page = 1;
//			else current_page = Integer.parseInt(r_page);
//			for_count		= list_setup_count;
//			p_no			= list_setup_count;				// 페이지수가 10
//			total_page = total_count / list_setup_count;		// 전체페이지수 계산 (if 23개 게시물이면 2)
//			if(current_page == 1) {
//				v_count		= 0;
//				a_count		= list_setup_count;
//				for_count	= 0;
//			} else{
//				v_count		= 0;
//				a_count		= p_no * current_page;
//				for_count	= a_count - list_setup_count;
//			}
//			if(total_page * list_setup_count != total_count)   total_page = total_page +1;
//			//******** page 끝*********/	
//								
//			model.addAttribute("v_count", v_count);
//			model.addAttribute("for_count", for_count);
//			model.addAttribute("a_count", a_count);
//			model.addAttribute("current_page", current_page);
//			model.addAttribute("total_page", total_page);
			viewPage = "/news/news_list";
			
		} else if(gubun.equals("view")) { //상세보기
			CommandNews news = new NewsView();
			news.execute(request, model);
//			news.execute(request, model, template);
//			String no = request.getParameter("t_no");
//			String hitQuery = " update track2_14_news set hit = hit+1 where no='"+no+"' ";
//			int result = template.update(hitQuery);
//			String query= " select a.no, a.title, a.content, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
//						  " from track2_14_news a, track2_14_member b "+ 
//						  " where a.reg_info = b.id "+
//					      " and a.no= '"+no+"' ";
//			BeanPropertyRowMapper<News_dto> News_dto = new BeanPropertyRowMapper<News_dto>(News_dto.class);
//			News_dto dto = (News_dto)template.queryForObject(query, News_dto);
//			model.addAttribute("t_dto", dto);
			viewPage = "/news/news_view";
			
		} else if(gubun.equals("writeForm")) { //등록페이지로 이동
			boolean goYN = CommonUtil.getCheckManager(request, model);
			if(goYN) viewPage = "/news/news_write";
			else viewPage = "/common_alert_page";
			
//			String ses_level = CommonUtil.getSessionLevel(request);
//			if(ses_level == null) {
//				model.addAttribute("t_msg","로그인 정보가 만료되었습니다.");
//				model.addAttribute("t_url","/Member");
//				viewPage = "/common_alert_page";
//			} else {
//				if(ses_level.equals("top")) {
//					viewPage = "/news/news_write";
//				}
//			}
			
		} else if(gubun.equals("save")) { //등록
			CommandNews news = new NewsSave();
			news.execute(request, model);
//			String maxQuery = "select max(no) from track2_14_news ";
//			String no = (String)template.queryForObject(maxQuery, String.class);
//			if(no == null) {
//				no = "W001";
//			} else {
//				no = no.substring(1); 
//				int num = Integer.parseInt(no); 
//				num = num + 1; 
//				no = CommonUtil.getLPad(Integer.toString(num), 3, "0");
//				no = "W"+no; 
//			}
//			String title = request.getParameter("t_title");
//			String content = request.getParameter("t_content");
//			String reg_info = "manager";
//			String reg_date = CommonUtil.getToday();
//			String query =  " insert into track2_14_news "+ 
//							" (no,title,content,reg_info,reg_date) "+ 
//							" values ('"+no+"', '"+title+"', '"+content+"', '"+reg_info+"', '"+reg_date+"') ";
//			int result = template.update(query);
			viewPage = "/common_alert_page";
			
		} else if(gubun.equals("updateForm")) { //수정페이지로 이동
			boolean goYN = CommonUtil.getCheckManager(request, model);
			if(goYN) {
				CommandNews news = new NewsView();
				news.execute(request, model);
				viewPage = "/news/news_update";
			} else {
				viewPage = "/common_alert_page";
			}
//			String no = request.getParameter("t_no");
//			String query= " select a.no, a.title, a.content, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
//						  " from track2_14_news a, track2_14_member b "+ 
//						  " where a.reg_info = b.id "+
//					      " and a.no= '"+no+"' ";
//			RowMapper<News_dto> newsdto = new BeanPropertyRowMapper<News_dto>(News_dto.class);
//			News_dto dto = template.queryForObject(query, newsdto);
//			model.addAttribute("t_dto", dto);
			
		} else if(gubun.equals("update")) { //수정
			CommandNews news = new NewsUpdate();
			news.execute(request, model);
//			String no = request.getParameter("t_no");
//			String title = request.getParameter("t_title");
//			String content = request.getParameter("t_content");
//			String query = " update track2_14_news "+ 
//						   " set title='"+title+"', content='"+content+"' "+ 
//						   " where no = '"+no+"' ";
//			int result = template.update(query);
			viewPage = "/common_alert_page";
			
		} else if(gubun.equals("delete")) { //삭제
			CommandNews news = new NewsDelete();
			news.execute(request, model);
//			String no = request.getParameter("t_no");
//			String query = " delete from track2_14_news "+ 
//					  	   " where no = '"+no+"' ";
//			int result = template.update(query);
			viewPage = "/common_alert_page";
		}
		return viewPage;
	}
}