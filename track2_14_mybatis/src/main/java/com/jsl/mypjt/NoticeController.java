package com.jsl.mypjt;

import java.util.*;


import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonUtil;
import dao.NoticeDao;
import dto.NextPrevVO;
import dto.NoticeVO;

@Controller
public class NoticeController {
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/NoticeDelete") //삭제
	public String noticeDelete(HttpServletRequest request) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		String no = request.getParameter("t_no");
		int result = dao.noticeDelete(no);
		return "redirect:/Notice";
	}
	
	@RequestMapping("/NoticeUpdate") //수정
	public String noticeUpdate(HttpServletRequest request) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		String no = request.getParameter("t_no");
		String title = request.getParameter("t_title");
		String content = request.getParameter("t_content");
		String update_id = "manager";
		String update_date = CommonUtil.getToday();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", no);
		map.put("m_title", title);
		map.put("m_content", content);
		map.put("m_update_id", update_id);
		map.put("m_update_date", update_date);
		int result = dao.noticeUpdate(map);
		System.out.println("result : "+result);
		return "redirect:/Notice";
	}
	
	@RequestMapping("/NoticeUpdateForm") //수정페이지로 이동
	public String noticeUpdateForm(HttpServletRequest request, Model model) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		String no = request.getParameter("t_no");
		NoticeVO vo = dao.getNoticeView_2(no);
		model.addAttribute("t_dto", vo);
		return "/notice/notice_update";
	}
	
	@RequestMapping("/NoticeView") //상세화면
	public String noticeVeiw(HttpServletRequest request, Model model) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		String no = request.getParameter("t_no");
		int hitCount = dao.noticeHit(no);
//		NoticeVO vo = dao.getNoticeView_1(no);
		NoticeVO vo = dao.getNoticeView_2(no);
		NextPrevVO npVo = dao.getNextPrev(no);
		model.addAttribute("t_np", npVo);
		model.addAttribute("t_dto", vo);
		return "/notice/notice_view";
	}
	
	@RequestMapping("/NoticeSave") //게시판 등록
	public String noticeSave(HttpServletRequest request, Model model) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		String no = dao.getMaxNo();
		if(no == null) {
			no ="N001";
		} else {
			no = no.substring(1); 
			int num = Integer.parseInt(no); 
			num = num + 1; 
			no = CommonUtil.getLPad(Integer.toString(num), 3, "0");
			no = "N"+no; 
		}
		String title = request.getParameter("t_title");
		String content = request.getParameter("t_content");
		String reg_id = "manager";
		String reg_date = CommonUtil.getToday();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", no);
		map.put("m_title", title);
		map.put("m_content", content);
		map.put("m_reg_id", reg_id);
		map.put("m_reg_date", reg_date);
		
		int result = dao.noticeSave(map);
		System.out.println("result : "+result);
		return "redirect:/Notice";
	}
	
	@RequestMapping("/NoticeWriteForm") //글쓰기폼으로 이동
	public String noticeWriteFomr() {
		return "/notice/notice_write";
	}
	
	@RequestMapping("/Notice") //목록 조회
	public String notice(HttpServletRequest request, Model model) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(select == null) {
			select = "title";
			search = "";
		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("t_sel", select);
//		map.put("t_sea", search);
//		ArrayList<NoticeVO> dtos_2 = dao.getNoticeList_2(map);	
		
		ArrayList<NoticeVO> dtos = dao.getNoticeList_3(select, search);
		
		//******** page 시작*********/
		String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
		int			current_page;					// 현재페이지 번호
		int			total_page;						// 총페이지 수
		int			total_count;					// 총레코드 수
		int			for_count;
				
		//***************************************//
		int			list_setup_count = 3;			// 한번에 출력될 List 수
		//***************************************//
		int			p_no;
		int			v_count;
		int			a_count;
		String		url				= null;	

		// 조회된 건수 구하기  total_count : 설정
		if(dtos == null) total_count =0;
		else total_count = dtos.size(); 

		// 페이지번호가 없으면 1페이지로 간주
		if(r_page.equals("")) current_page = 1;
		else current_page = Integer.parseInt(r_page);
					
		for_count		= list_setup_count;
		p_no			= list_setup_count;				// 페이지수가 10
		total_page = total_count / list_setup_count;
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
			
		request.setAttribute("v_count", v_count);
		request.setAttribute("for_count", for_count);
		request.setAttribute("a_count", a_count);
		request.setAttribute("current_page", current_page);
		request.setAttribute("total_page", total_page);				
		//******** page 끝*********/			
		
		model.addAttribute("t_dtos", dtos);
		model.addAttribute("t_select", select);
		model.addAttribute("t_search", search);
		
		return "/notice/notice_list";
	}
}