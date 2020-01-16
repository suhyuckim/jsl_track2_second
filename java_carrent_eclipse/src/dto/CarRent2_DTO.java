package dto;

public class CarRent2_DTO {
	String rent_id, name, id, car_name, car_id, rent_start_date, rent_return_date, driving_km2;
	int driving_total_km, driving_km;
	
	public CarRent2_DTO() {}
	
	public String getDriving_km2() {
		return driving_km2;
	}

	public void setDriving_km2(String driving_km2) {
		this.driving_km2 = driving_km2;
	}

	public CarRent2_DTO(String rent_id, String name, String id, String car_name, 
			String car_id, String rent_start_date, String rent_return_date){
		this.rent_id = rent_id;
		this.name = name;
		this.id = id;
		this.car_name = car_name;
		this.car_id = car_id;
		this.rent_start_date = rent_start_date;
		this.rent_return_date = rent_return_date;
	}
	
	public CarRent2_DTO(String rent_id, String name, String id, String car_name, 
						String car_id, int driving_km, String rent_start_date, String rent_return_date){
		this.rent_id = rent_id;
		this.name = name;
		this.id = id;
		this.car_name = car_name;
		this.car_id = car_id;
		this.driving_km = driving_km;
		this.rent_start_date = rent_start_date;
		this.rent_return_date = rent_return_date;
	}	
	public int getDriving_km() {
		return driving_km;
	}

	public void setDriving_km(int driving_km) {
		this.driving_km = driving_km;
	}

	public void setRent_id(String rent_id) {
		this.rent_id = rent_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public void setDriving_total_km(int driving_total_km) {
		this.driving_total_km = driving_total_km;
	}
	public void setRent_start_date(String rent_start_date) {
		this.rent_start_date = rent_start_date;
	}
	public void setRent_return_date(String rent_return_date) {
		this.rent_return_date = rent_return_date;
	}
	public String getRent_id() {
		return rent_id;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getCar_name() {
		return car_name;
	}
	public String getCar_id() {
		return car_id;
	}
	public int getDriving_total_km() {
		return driving_total_km;
	}
	public String getRent_start_date() {
		return rent_start_date;
	}
	public String getRent_return_date() {
		return rent_return_date;
	}
}
