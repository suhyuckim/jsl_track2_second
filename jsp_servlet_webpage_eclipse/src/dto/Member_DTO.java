package dto;

public class Member_DTO {
	String id, pw, name, phone,	email_1, email_2, informaiton, reg_date, status, update_date;
	
	public Member_DTO() {}
	
	public Member_DTO(String id, String pw, String name, String phone, String email_1, String email_2,
					  String informaiton, String reg_date, String status) {
		this.id 		 = id;
		this.pw 		 = pw;
		this.name   	 = name;
		this.phone 		 = phone;
		this.email_1 	 = email_1;
		this.email_2 	 = email_2;
		this.informaiton = informaiton;
		this.reg_date 	 = reg_date;
		this.status 	 = status;	
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail_1() {
		return email_1;
	}

	public void setEmail_1(String email_1) {
		this.email_1 = email_1;
	}

	public String getEmail_2() {
		return email_2;
	}

	public void setEmail_2(String email_2) {
		this.email_2 = email_2;
	}

	public String getInformaiton() {
		return informaiton;
	}

	public void setInformaiton(String informaiton) {
		this.informaiton = informaiton;
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

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}		
}