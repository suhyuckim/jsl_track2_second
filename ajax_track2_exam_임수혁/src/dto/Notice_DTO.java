package dto;

public class Notice_DTO {
	String notice_no
	,title
	,content
	,file_name
	,reg_id
	,reg_date;
	int hit, likecount;
	
	public Notice_DTO() {}
	public Notice_DTO(String notice_no, String title, String content, String file_name, String reg_id, String reg_date,
			int hit, int likecount) {
		super();
		this.notice_no = notice_no;
		this.title = title;
		this.content = content;
		this.file_name = file_name;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.hit = hit;
		this.likecount = likecount;
	}
	public Notice_DTO(String notice_no, String title, String content, String file_name, String reg_id, String reg_date,
			int hit) {
		super();
		this.notice_no = notice_no;
		this.title = title;
		this.content = content;
		this.file_name = file_name;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	public String getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(String notice_no) {
		this.notice_no = notice_no;
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
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
}	