package dto;

public class CarRent_DTO {
	String rent_id, car_id, member_id, driving_km, rent_start_date, rent_return_date;

	public String getRent_id() {
		return rent_id;
	}

	public void setRent_id(String rent_id) {
		this.rent_id = rent_id;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getDriving_km() {
		return driving_km;
	}

	public void setDriving_km(String driving_km) {
		this.driving_km = driving_km;
	}

	public String getRent_start_date() {
		return rent_start_date;
	}

	public void setRent_start_date(String rent_start_date) {
		this.rent_start_date = rent_start_date;
	}

	public String getRent_return_date() {
		return rent_return_date;
	}

	public void setRent_return_date(String rent_return_date) {
		this.rent_return_date = rent_return_date;
	}
	
	
}
