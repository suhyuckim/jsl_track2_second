package command.faq;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import common.CommonUtil;
import dao.Faq_dao;

public class CommandFaqSave implements CommandFaq {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		Faq_dao dao 	= new Faq_dao();
		String no       = dao.getFaqNo();
		String question = request.getParameter("t_question");
		String answer   = request.getParameter("t_answer");
		String seq  	= request.getParameter("t_seq");
		if(seq.contentEquals("")) seq = "0";
		String reg_member_info = "manager";
		String reg_date = CommonUtil.getToday();
		
		int result = dao.saveFaq(no,question,answer,seq,reg_member_info,reg_date);
		
		String msg = "";
		if(result == 0) msg = " 등록 실패하였습니다. ";
		else msg = " 정상적으로 등록되었습니다. ";
		
		model.addAttribute("t_msg", msg);
		model.addAttribute("t_url", "/Faq");
	}
}