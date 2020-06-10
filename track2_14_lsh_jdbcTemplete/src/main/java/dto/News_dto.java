package dto;

public class News_dto {
	String no, title, content, hit, reg_info, reg_date;

	public void setNo(String no) {
		this.no = no;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public void setReg_info(String reg_info) {
		this.reg_info = reg_info;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getHit() {
		return hit;
	}

	public String getReg_info() {
		return reg_info;
	}

	public String getReg_date() {
		return reg_date;
	}
}