package dto;

public class User_dto {
	String id
	,pw
	,name
	,tel
	,email_1
	,email_2
	,info_yn
	,reg_date
	,ing_yn
	,exit_date;
	
	public User_dto(String id, String name, String tel, String email_1, String email_2, String info_yn,
			String reg_date, String ing_yn, String exit_date) { //매니저 회원 상세보기용
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.info_yn = info_yn;
		this.reg_date = reg_date;
		this.ing_yn = ing_yn;
		this.exit_date = exit_date;
	}
	
	public User_dto(String id, String name, String tel, String email_1, String email_2, 
			String reg_date, String ing_yn) { //매니저 회원목록 확인용
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.reg_date = reg_date;
		this.ing_yn = ing_yn;
	}
	
	public User_dto(String id, String pw, String name, String tel, String email_1, String email_2, String info_yn,
			String reg_date, String ing_yn, String exit_date) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.info_yn = info_yn;
		this.reg_date = reg_date;
		this.ing_yn = ing_yn;
		this.exit_date = exit_date;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getInfo_yn() {
		return info_yn;
	}

	public void setInfo_yn(String info_yn) {
		this.info_yn = info_yn;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getIng_yn() {
		return ing_yn;
	}

	public void setIng_yn(String ing_yn) {
		this.ing_yn = ing_yn;
	}

	public String getExit_date() {
		return exit_date;
	}

	public void setExit_date(String exit_date) {
		this.exit_date = exit_date;
	}
}