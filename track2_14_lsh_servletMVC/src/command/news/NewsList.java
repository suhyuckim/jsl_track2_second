package command.news;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import common.CommonUtil;
import dao.News_dao;
import dto.News_dto;

public class NewsList implements NewsAcommand{
	@Override
	public void execute(HttpServletRequest request){
		News_dao dao = new News_dao();
		String sel = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(sel == null) {
			sel = "title";
			search ="";
		}
		ArrayList<News_dto> dtos =  dao.getNewsList(sel, search);
		request.setAttribute("t_dtos", dtos);
		request.setAttribute("t_select", sel);
		request.setAttribute("t_search", search);
		
		//******** page ����*********/
		String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
		int			current_page;					// ���������� ��ȣ
		int			total_page;						// �������� ��
		int			total_count;					// �ѷ��ڵ� ��
		int			for_count;
		
		//***************************************//
		int			list_setup_count = 5;			// �ѹ��� ��µ� List ��
		//***************************************//
		int			p_no;
		int			v_count;
		int			a_count;
		String		url				= null;	
		// ��ȸ�� �Ǽ� ���ϱ�  total_count : ����
			if(dtos == null) total_count =0;
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
			
			request.setAttribute("v_count", v_count);
			request.setAttribute("for_count", for_count);
			request.setAttribute("a_count", a_count);
			request.setAttribute("current_page", current_page);
			request.setAttribute("total_page", total_page);
	}
}