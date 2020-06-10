package dto;

public class NoticeVO {
	String no, title, content, reg_info, reg_date, hit, update_id, update_date;
/*
	public NoticeVO(String no, String title, String content, String reg_info, String reg_date, String hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_info = reg_info;
		this.reg_date = reg_date;
		this.hit = hit;
	}
*/
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

	public String getReg_info() {
		return reg_info;
	}

	public void setReg_info(String reg_info) {
		this.reg_info = reg_info;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	
}