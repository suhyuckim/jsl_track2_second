package dto;

public class News_dto {
	String no, title, content, hit, reg_info, reg_date;

	public News_dto(String no, String title, String hit, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	
	public News_dto(String no, String title, String content, String hit, String reg_info, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_info = reg_info;
		this.reg_date = reg_date;
	}

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

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
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
}
