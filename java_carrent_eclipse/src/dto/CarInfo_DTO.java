package dto;

public class CarInfo_DTO {
	String car_id, car_name, produce, produce_ym, status;
	int driving_total_km;

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}

	public String getProduce_ym() {
		return produce_ym;
	}

	public void setProduce_ym(String produce_ym) {
		this.produce_ym = produce_ym;
	}

	public int getDriving_total_km() {
		return driving_total_km;
	}

	public void setDriving_total_km(int driving_total_km) {
		this.driving_total_km = driving_total_km;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}