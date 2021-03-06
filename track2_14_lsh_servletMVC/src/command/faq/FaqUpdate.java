package command.faq;

import javax.servlet.http.HttpServletRequest;

import common.CommonUtil;
import dao.Faq_dao;

public class FaqUpdate implements FaqCommand {

	@Override
	public void execute(HttpServletRequest request) {
		Faq_dao dao 	= new Faq_dao();
		String no       = request.getParameter("t_no");
		String question = request.getParameter("t_question");
		String answer   = request.getParameter("t_answer");
		String seq  	= request.getParameter("t_seq");
		if(seq.contentEquals("")) seq = "0";
		String update_member_info = "101";
		String update_date = CommonUtil.getToday();
		
		int result = dao.updateFaq(no,question,answer,seq,update_member_info,update_date);
		
		String url = request.getContextPath()+"/Faq";
		String msg = "";
		if(result == 0) msg = " 수정 실패하였습니다. ";
		else msg = " 정상적으로 수정되었습니다. ";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}
}