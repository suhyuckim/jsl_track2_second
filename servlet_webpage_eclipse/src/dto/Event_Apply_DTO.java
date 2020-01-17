package dto;

public class Event_Apply_DTO {
	String event_no
	,event_no_m
	,title
	,content
	,reg_id
	,reg_date
	,status;
	
	public Event_Apply_DTO() {}
	public Event_Apply_DTO(String event_no, String event_no_m, String title, String content, String reg_id,
			String reg_date, String status) {
		super();
		this.event_no = event_no;
		this.event_no_m = event_no_m;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.status = status;
	}

	public String getEvent_no() {
		return event_no;
	}

	public void setEvent_no(String event_no) {
		this.event_no = event_no;
	}

	public String getEvent_no_m() {
		return event_no_m;
	}

	public void setEvent_no_m(String event_m_no) {
		this.event_no_m = event_no_m;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}