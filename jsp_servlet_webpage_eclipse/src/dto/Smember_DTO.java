package dto;

public class Smember_DTO {
	String id
	,name
	,area;
	int age;
	
	public Smember_DTO(){}	
	public Smember_DTO(String id, String name, String area, int age) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}