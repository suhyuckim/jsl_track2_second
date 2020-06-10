package dto;

public class Faq_dto {
	String no, 
	question, 
	answer, 
	seq, 
	reg_member_info, 
	reg_date, 
	update_member_info, 
	update_date;

	public Faq_dto(String no, String question, String answer) {
		super();
		this.no = no;
		this.question = question;
		this.answer = answer;
	}
	
	public Faq_dto(String no, String question, String answer, String seq, String reg_member_info, String reg_date,
			String update_member_info, String update_date) {
		super();
		this.no = no;
		this.question = question;
		this.answer = answer;
		this.seq = seq;
		this.reg_member_info = reg_member_info;
		this.reg_date = reg_date;
		this.update_member_info = update_member_info;
		this.update_date = update_date;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getReg_member_info() {
		return reg_member_info;
	}

	public void setReg_member_info(String reg_member_info) {
		this.reg_member_info = reg_member_info;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getUpdate_member_info() {
		return update_member_info;
	}

	public void setUpdate_member_info(String update_member_info) {
		this.update_member_info = update_member_info;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
}