package springmvc.aaa;

import java.util.Arrays;

public class User {
	
	private String name;
	private Integer age;
	private Float income;
	private Boolean isMarry;
	private String[] interests;
	private Long id;
	private String userName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	public Boolean getIsMarry() {
		return isMarry;
	}
	public void setIsMarry(Boolean isMarry) {
		this.isMarry = isMarry;
	}
	public String[] getInterests() {
		return interests;
	}
	public void setInterests(String[] interests) {
		this.interests = interests;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", income=" + income + ", isMarry=" + isMarry + ", interests="
				+ Arrays.toString(interests) + "]";
	}

}
