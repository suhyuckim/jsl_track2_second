package com.jsl.mypjt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import common.CommonUtil;
import common.FileDownload;
import common.FileUploadService;
import dao.ResourceDao;
import dto.ResourceVO;

@Controller
public class ResuorceController {
	@Autowired
	private SqlSession sqlSession;

	ResourceDao dao = null;
	
	@RequestMapping("/ResourceUpdate")
	public String resourceUpdate(HttpServletRequest request, 
								 @RequestParam("t_file_1") MultipartFile file_A,
								 @RequestParam("t_file_2") MultipartFile file_B) {
		dao = sqlSession.getMapper(ResourceDao.class);
		FileUploadService fileService = new FileUploadService();
		String no = request.getParameter("t_no");

		//첨부1
		String saveFileName_1 = "";
		String delFile_1 = request.getParameter("t_delfile_1");
		boolean tfDel_1 = false;
		if(delFile_1 != null) {
			File df = new File(CommonUtil.resFileDir, delFile_1);
			tfDel_1 = df.delete();
			System.out.println("1삭제 : "+tfDel_1);
		} else {
			saveFileName_1 = request.getParameter("t_ori_file_1");
		}
		String fileNameA = file_A.getOriginalFilename();
		if (!fileNameA.equals("")) { 
			String delF = request.getParameter("t_ori_file_1");
			if(!delF.equals("") && tfDel_1 == false) {
				File fd = new File(CommonUtil.resFileDir, delF);
				boolean aa = fd.delete();
				System.out.println("2삭제 : " +aa);
			}
			saveFileName_1 = no + "-1-" + fileNameA;
			boolean tf = fileService.restore(file_A, saveFileName_1, "resDir");
		}
		
		//첨부2
		String saveFileName_2 = "";
		String delFile_2 = request.getParameter("t_delfile_2");
		boolean tfDel_2 = false;
		if(delFile_2 != null) {
			File df = new File(CommonUtil.resFileDir, delFile_2);
			tfDel_2 = df.delete();
			System.out.println("1삭제 : "+tfDel_2);
		} else {
			saveFileName_2 = request.getParameter("t_ori_file_2");
		}
		String fileNameB = file_B.getOriginalFilename();
		if (!fileNameB.equals("")) { 
			String delF = request.getParameter("t_ori_file_2");
			if(!delF.equals("") && tfDel_2 == false) {
				File fd = new File(CommonUtil.resFileDir, delF);
				boolean aa = fd.delete();
				System.out.println("2삭제 : " +aa);
			}
			saveFileName_2 = no + "-2-" + fileNameB;
			boolean tf = fileService.restore(file_B, saveFileName_2, "resDir");
		}
		String title = request.getParameter("t_title");
		String content = request.getParameter("t_content");
		String update_id = "manager";
		String update_date = CommonUtil.getToday();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t_no", no);
		map.put("t_title", title);
		map.put("t_content", content);
		map.put("t_file_1", saveFileName_1);
		map.put("t_file_2", saveFileName_2);
		map.put("t_update_id", update_id);
		map.put("t_update_date", update_date);
		
		int result = dao.resourceUpdate(map);
		return "redirect:/Resource";
	}
	
	@RequestMapping("/ResourceUpdateForm")
	public String resourceUpdateForm(HttpServletRequest request, Model model) {
		dao = sqlSession.getMapper(ResourceDao.class);
		String no = request.getParameter("t_no");
		ResourceVO vo = dao.getResourceView(no);
		model.addAttribute("t_dto", vo);
		return "/resourceRoom/resource_update";
	}

	@RequestMapping("/ResFileDown")
	public void resFileDown(HttpServletRequest request, HttpServletResponse response){
		FileDownload fd = new FileDownload();
		fd.fileDown(request, response);
	}
	
	@RequestMapping("/ResourceView")
	public String resourceView(HttpServletRequest request, Model model) {
		dao = sqlSession.getMapper(ResourceDao.class);
		String no = request.getParameter("t_no");
//		int hitCount = dao.ResourceHit(no);
		ResourceVO vo = dao.getResourceView(no);
		model.addAttribute("t_dto", vo);
		return "/resourceRoom/resource_view";
	}
	
	@RequestMapping("/ResourceSave")
	public String resourceSave(HttpServletRequest request, @RequestParam("t_file_1") MultipartFile file_A,
			@RequestParam("t_file_2") MultipartFile file_B) {
		dao = sqlSession.getMapper(ResourceDao.class);

		String no = dao.getMaxNo();
		if (no == null) {
			no = "R001";
		} else {
			no = no.substring(1);
			int num = Integer.parseInt(no);
			num = num + 1;
			no = CommonUtil.getLPad(Integer.toString(num), 3, "0");
			no = "R" + no;
		}

		FileUploadService fileService = new FileUploadService();
		String fileNameA = file_A.getOriginalFilename();
		if (!fileNameA.equals("")) {
			fileNameA = no + "-1-" + fileNameA;
			boolean tf = fileService.restore(file_A, fileNameA, "resDir");
		}

		String fileNameB = file_B.getOriginalFilename();
		if (!fileNameB.equals("")) {
			fileNameB = no + "-2-" + fileNameB;
			boolean tf = fileService.restore(file_B, fileNameB, "resDir");
		}
		String title = request.getParameter("t_title");
		String content = request.getParameter("t_content");
		String reg_id = request.getParameter("t_reg_id");
		String reg_date = CommonUtil.getToday();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t_no", no);
		map.put("t_title", title);
		map.put("t_content", content);
		map.put("t_file_1", fileNameA);
		map.put("t_file_2", fileNameB);
		map.put("t_reg_id", reg_id);
		map.put("t_reg_date", reg_date);

		int result = dao.resourceSave(map);
		return "redirect:/Resource";
	}

	@RequestMapping("/Resource")
	public String resource(HttpServletRequest request, Model model) {
		dao = sqlSession.getMapper(ResourceDao.class);
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if (select == null) {
			select = "title";
			search = "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t_sel", select);
		map.put("t_sea", search);

//		ArrayList<ResourceVO> dtos = dao.getResourceList();
		ArrayList<ResourceVO> dtos = dao.getResourceList_2(map);

		model.addAttribute("t_dtos", dtos);
		model.addAttribute("t_select", select);
		model.addAttribute("t_search", search);

		// ******** page 시작*********/
		String r_page = CommonUtil.checkNull(request.getParameter("r_page"));
		int current_page; // 현재페이지 번호
		int total_page; // 총페이지 수
		int total_count; // 총레코드 수
		int for_count;

		// ***************************************//
		int list_setup_count = 3; // 한번에 출력될 List 수
		// ***************************************//
		int p_no;
		int v_count;
		int a_count;
		String url = null;

		// 조회된 건수 구하기 total_count : 설정
		if (dtos == null)
			total_count = 0;
		else
			total_count = dtos.size();

		// 페이지번호가 없으면 1페이지로 간주
		if (r_page.equals(""))
			current_page = 1;
		else
			current_page = Integer.parseInt(r_page);

		for_count = list_setup_count;
		p_no = list_setup_count; // 페이지수가 10
		total_page = total_count / list_setup_count;
		if (current_page == 1) {
			v_count = 0;
			a_count = list_setup_count;
			for_count = 0;
		} else {
			v_count = 0;
			a_count = p_no * current_page;
			for_count = a_count - list_setup_count;
		}
		if (total_page * list_setup_count != total_count)
			total_page = total_page + 1;

		request.setAttribute("v_count", v_count);
		request.setAttribute("for_count", for_count);
		request.setAttribute("a_count", a_count);
		request.setAttribute("current_page", current_page);
		request.setAttribute("total_page", total_page);
		// ******** page 끝*********/

		return "/resourceRoom/resource_list";
	}

	@RequestMapping("/ResourceWriteForm")
	public String resourceWriteForm() {
		return "/resourceRoom/resource_write";
	}
}