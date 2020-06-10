package command.news;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ui.Model;

import common.CommonUtil;
import dao.News_dao;
import dto.News_dto;

public class NewsList implements CommandNews{
	
	@Override
	public void execute(HttpServletRequest request, Model model) {
//	public void execute(HttpServletRequest request, Model model, JdbcTemplate template) {
		News_dao dao = new News_dao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(select == null) {
			select = "title";
			search = "";
		}
//		RowMapper<News_dto> News_dtoS = new BeanPropertyRowMapper<News_dto>(News_dto.class);	
//		String query = " select a.no, a.title, b.name as reg_info, to_char(a.reg_date,'yyyy-MM-dd') as reg_date, a.hit "+ 
//					   " from track2_14_news a, track2_14_member b "+ 
//					   " where a.reg_info = b.id "+
//					   " and "+select+" like '%"+search+"%' "+
//				       " order by no desc ";
//		ArrayList<News_dto> dtos = (ArrayList<News_dto>)template.query(query, News_dtoS);
		
//		ArrayList<News_dto> dtos = dao.getNewsList(select, search, template);
		ArrayList<News_dto> dtos = dao.getNewsList2(select, search);
		
		model.addAttribute("t_dtos",dtos);
		model.addAttribute("t_select",select);
		model.addAttribute("t_search",search);
		
		//******** page ����*********/
		String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
		int			current_page;					// ���������� ��ȣ
		int			total_page;						// �������� ��
		int			total_count;					// �ѷ��ڵ� ��
		int			for_count;
				
		//***************************************//
		int			list_setup_count = 3;			// �ѹ��� ��µ� List ��
		//***************************************//
		int			p_no;
		int			v_count;
		int			a_count;
		String		url				= null;	
		// ��ȸ�� �Ǽ� ���ϱ�  total_count : ����
		if(dtos == null) total_count=0;
		else total_count = dtos.size(); 

		// ��������ȣ�� ������ 1�������� ����
		if(r_page.equals("")) current_page = 1;
		else current_page = Integer.parseInt(r_page);
		for_count		= list_setup_count;
		p_no			= list_setup_count;				// ���������� 10
		total_page = total_count / list_setup_count;		// ��ü�������� ��� (if 23�� �Խù��̸� 2)
		if(current_page == 1) {
			v_count		= 0;
			a_count		= list_setup_count;
			for_count	= 0;
		} else{
			v_count		= 0;
			a_count		= p_no * current_page;
			for_count	= a_count - list_setup_count;
		}
		if(total_page * list_setup_count != total_count)   total_page = total_page +1;
		//******** page ��*********/	
							
		model.addAttribute("v_count", v_count);
		model.addAttribute("for_count", for_count);
		model.addAttribute("a_count", a_count);
		model.addAttribute("current_page", current_page);
		model.addAttribute("total_page", total_page);
	}
}