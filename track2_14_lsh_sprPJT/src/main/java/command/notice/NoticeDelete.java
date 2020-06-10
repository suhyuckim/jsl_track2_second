package command.notice;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import dao.Notice_dao;

public class NoticeDelete implements CommandNotice {
	@Resource(name="noticeFileDir")
	String saveDir;
	
	@Override
	public void execute(HttpServletRequest request, Model model) {
		Notice_dao dao = new Notice_dao();
		String no = request.getParameter("t_no");
		String dir = (String)request.getAttribute("file_dir");
		String delFile = request.getParameter("t_fileName");
		if(!delFile.equals("")) {
			File df = new File(dir, delFile);
			boolean delTF = df.delete();
			if(!delTF) System.out.println("�������� ÷�� ���� ���� ���� NoticeDelete.java");
		}
		int result = dao.deleteNotice(no);
		
		String msg = "";
		String url = "/Notice";
		
		if(result == 0) msg = "���� �����Ͽ����ϴ�.";
		else msg = "���������� �����Ǿ����ϴ�.";
		
		model.addAttribute("t_url", url);
		model.addAttribute("t_msg", msg);
	}
}