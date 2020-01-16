package dto;

public class Faq_DTO {
	String faq_id
	,question
	,answer
	,reg_id
	,reg_date;

	public Faq_DTO() {}
	public Faq_DTO(String faq_id, String question, String answer, String reg_id, String reg_date) {
		super();
		this.faq_id = faq_id;
		this.question = question;
		this.answer = answer;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}

	public String getFaq_id() {
		return faq_id;
	}

	public void setFaq_id(String faq_id) {
		this.faq_id = faq_id;
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

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
}