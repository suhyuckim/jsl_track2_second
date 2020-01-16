package dto;

public class Member_DTO {
	String id
	,pw
	,name
	,birth
	,area
	,address
	,telecom
	,phone_1
	,phone_2
	,phone_3
	,email_1
	,email_2
	,reg_date
	,update_date
	,sex
	,status;

	public Member_DTO(){}
	
	public Member_DTO(String id, String name, String area,
					  String phone_1, String phone_2, String phone_3, String email_1, String email_2, String reg_date,
					  String status) { //멤버 리스트 회원조회
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.phone_1 = phone_1;
		this.phone_2 = phone_2;
		this.phone_3 = phone_3;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.reg_date = reg_date;
		this.status = status;
	}
	
	public Member_DTO(String id, String pw, String name, String birth, String area, String address, String telecom,
			String phone_1, String phone_2, String phone_3, String email_1, String email_2, String reg_date,
			String update_date, String sex, String status) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.area = area;
		this.address = address;
		this.telecom = telecom;
		this.phone_1 = phone_1;
		this.phone_2 = phone_2;
		this.phone_3 = phone_3;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.sex = sex;
		this.status = status;
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelecom() {
		return telecom;
	}

	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}

	public String getPhone_1() {
		return phone_1;
	}

	public void setPhone_1(String phone_1) {
		this.phone_1 = phone_1;
	}

	public String getPhone_2() {
		return phone_2;
	}

	public void setPhone_2(String phone_2) {
		this.phone_2 = phone_2;
	}

	public String getPhone_3() {
		return phone_3;
	}

	public void setPhone_3(String phone_3) {
		this.phone_3 = phone_3;
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

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
