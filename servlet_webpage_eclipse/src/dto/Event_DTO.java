package dto;

public class Event_DTO {
	String event_no, title, content, reg_id, reg_date, start_date, end_date, file_name_1;
	int hit;
	public Event_DTO() {}
	public Event_DTO(String event_no, String title, String content, 
			String reg_id, String reg_date, String start_date,
			String end_date, String file_name_1, int hit) { //이벤트 등록 file_name_1 추가
		super();
		this.event_no = event_no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.start_date = start_date;
		this.end_date = end_date;
		this.file_name_1 = file_name_1;
		this.hit = hit;
	}

	public String getEvent_no() {
		return event_no;
	}

	public void setEvent_no(String event_no) {
		this.event_no = event_no;
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

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getFile_name_1() {
		return file_name_1;
	}

	public void setFile_name_1(String file_name_1) {
		this.file_name_1 = file_name_1;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
}