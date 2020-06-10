package jsl.spr.mypjt;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import command.notice.CommandNotice;
import command.notice.NoticeDelete;
import command.notice.NoticeList;
import command.notice.NoticeSave;
import command.notice.NoticeUpdate;
import command.notice.NoticeView;
import common.CommonUtil;
import common.FileUploadService;
import dao.Notice_dao;
import dto.Notice_dto;

@Controller
public class NoticeController {
	@Resource(name="noticeFileDir")
	String saveDir;
	
	@RequestMapping("/NoticeSave")
	public String noticeSave(HttpServletRequest request, @RequestParam("fileName_a") MultipartFile file_A,
			@RequestParam("fileName_b") MultipartFile file_B, Model model) { //등록
		String level = CommonUtil.getSessionLevel(request);
		if(level == null) {
			model.addAttribute("t_url", "/User");
			model.addAttribute("t_msg", "로그인 정보가 만료되었습니다.");
			return "/common_alert_page";
		} else {
			Notice_dao dao = new Notice_dao();
			String no = dao.getNoticeNo();
			String title = request.getParameter("t_title");
			String content = request.getParameter("t_content");
			HttpSession session = request.getSession();
			String reg_id = (String)session.getAttribute("session_id");
			String reg_date = CommonUtil.getToday();
			String fileNameA = file_A.getOriginalFilename();
			String saveFileNameA = "";
			
			if(fileNameA != "") {
				saveFileNameA = no+"_"+fileNameA;
				FileUploadService fileUp = new FileUploadService();
				//String saveDir = "C:/track2online/track2_14_lsh_sprPJT/src/main/webapp/file_room/notice/";
				boolean fileTF = fileUp.restore(file_A, saveFileNameA, saveDir);
				//System.out.println("A 첨부 성공 여부 : "+fileTF);
			}

			Notice_dto dto = new Notice_dto(no,title,content,saveFileNameA,reg_id,"",reg_date,"");
			int result = dao.saveNotice(dto);
			
			String msg = "";
			String url = "/Notice";
			
			if(result == 0) msg = "등록에 오류가 발생하였습니다.";
			else msg = "성공적으로 등록되었습니다.";
			
			model.addAttribute("t_msg", msg);
			model.addAttribute("t_url", url);
			
			return "/common_alert_page";
		}
	}
	
	@RequestMapping("/NoticeUpdate")
	public String noticeUpdate(HttpServletRequest request, @RequestParam("fileName_a") MultipartFile file_A, Model model) { //수정
		String level = CommonUtil.getSessionLevel(request);
		if(level == null) {
			model.addAttribute("t_url", "/User");
			model.addAttribute("t_msg", "로그인 정보가 만료되었습니다.");
			return "/common_alert_page";
		} else {
			Notice_dao dao = new Notice_dao();
			String no 	   = request.getParameter("t_no");
			String title   = request.getParameter("t_title");
			String content = request.getParameter("t_content");
			
			String delFile = request.getParameter("t_delCheckBox");
			String oriFile = request.getParameter("t_ori_fileName");
			
			String fileNameA = file_A.getOriginalFilename();
			String saveFileNameA = "";
			
			if(delFile != null) {
				File df = new File(saveDir,delFile);
				df.delete();
			} else {
				saveFileNameA = oriFile;
			}
			if(fileNameA != "") {
				if(!oriFile.equals("")) {
					File df = new File(saveDir,oriFile);
					df.delete();
				}
				saveFileNameA = no+"_"+fileNameA;
				FileUploadService fileUp = new FileUploadService();
				//String saveDir = "C:/track2online/track2_14_lsh_sprPJT/src/main/webapp/file_room/notice/";
				boolean fileTF = fileUp.restore(file_A, saveFileNameA, saveDir);
			}
			String url = "/Notice";
			String msg = "";
			int result = dao.updateNotice(no, title, content, saveFileNameA);
			
			if(result == 0) msg = "수정 실패하였습니다.";
			else msg = "정상적으로 수정되었습니다.";
			
			model.addAttribute("t_msg", msg);
			model.addAttribute("t_url", url);
			
			return "/common_alert_page";
		}
	}
	
	@RequestMapping("/Notice")
	public String notice(HttpServletRequest request, Model model) {
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun = "list";
		String viewPage = "";
		if(gubun.equals("list")) { //목록
			CommandNotice noti = new NoticeList();
			noti.execute(request, model);
			viewPage = "/notice/notice_list";
			
		} else if(gubun.equals("writeForm")) { //등록페이지로 이동
			String level = CommonUtil.getSessionLevel(request);
			if(level == null) {
				model.addAttribute("t_url", "/User");
				model.addAttribute("t_msg", "로그인 정보가 만료되었습니다.");
				viewPage = "/common_alert_page";
			} else {
				viewPage = "/notice/notice_wirte";
			}
			
		} else if(gubun.equals("view")) { //상세보기
			CommandNotice noti = new NoticeView();
			noti.execute(request, model);
			viewPage = "/notice/notice_view";
			
		}  else if(gubun.equals("updateForm")) { //수정페이지로 이동
//			HttpSession session = request.getSession();
//			String level = (String)session.getAttribute("session_level");
			String level = CommonUtil.getSessionLevel(request);
			if(level == null) {
				model.addAttribute("t_url", "/User");
				model.addAttribute("t_msg", "로그인 정보가 만료되었습니다.");
				viewPage = "/common_alert_page";
			} else {
				CommandNotice noti = new NoticeView();
				noti.execute(request, model);
				viewPage = "/notice/notice_update";
			} 
		} else if(gubun.equals("delete")) { //삭제
			String level = CommonUtil.getSessionLevel(request);
			if(level == null) {
				model.addAttribute("t_url", "/User");
				model.addAttribute("t_msg", "로그인 정보가 만료되었습니다.");
			} else {
				request.setAttribute("file_dir", saveDir);
				CommandNotice noti = new NoticeDelete();
				noti.execute(request, model);
			}
				viewPage = "/common_alert_page";
		}
		return viewPage;
	}
}