package command.news;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import dao.News_dao;
import dto.News_dto;

public class NewsView implements CommandNews{
	
	@Override
//	public void execute(HttpServletRequest request, Model model, JdbcTemplate template) {
	public void execute(HttpServletRequest request, Model model) {
		News_dao dao = new News_dao();
		String no = request.getParameter("t_no");
		dao.hitCount2(no);
		
//		dao.hitCount(no, template);		
//		String hitQuery = " update track2_14_news set hit = hit+1 where no='"+no+"' ";
//		int result = template.update(hitQuery);
//		String query= " select a.no, a.title, a.content, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
//					  " from track2_14_news a, track2_14_member b "+ 
//					  " where a.reg_info = b.id "+
//				      " and a.no= '"+no+"' ";
//		BeanPropertyRowMapper<News_dto> News_dto = new BeanPropertyRowMapper<News_dto>(News_dto.class);
//		News_dto dto = (News_dto)template.queryForObject(query, News_dto);
//		News_dto dto = dao.getNewsView(no, template);
		News_dto dto = dao.getNewsView2(no);
		
		model.addAttribute("t_dto", dto);
	}
}
