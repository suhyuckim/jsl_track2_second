package dto;

public class Notice_dto {
	String no
	,title
	,content
	,file_name_1
	,reg_id
	,reg_name
	,reg_date
	,hit;
	
	public Notice_dto(String no, String title,  String file_name_1, String reg_date, String hit) {
		super();
		this.no = no;
		this.title = title;
		this.file_name_1 = file_name_1;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	public Notice_dto(String no, String title, String content, String file_name_1, String reg_id, String reg_name,
			String reg_date, String hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.file_name_1 = file_name_1;
		this.reg_id = reg_id;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.hit = hit;
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
	public String getFile_name_1() {
		return file_name_1;
	}
	public void setFile_name_1(String file_name_1) {
		this.file_name_1 = file_name_1;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
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
}