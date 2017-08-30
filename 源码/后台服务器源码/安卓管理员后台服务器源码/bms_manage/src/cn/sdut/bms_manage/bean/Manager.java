package cn.sdut.bms_manage.bean;

public class Manager {
	private String name;
	private String phone;
	private String password;
	private String employnum;
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(String employnum, String name, String phone, String password) {
		super();
		this.employnum = employnum;
		this.name = name;
		this.phone = phone;
		this.password = password;
	}
	public String getEnploynum() {
		return employnum;
	}
	public void setEnploynum(String employnum) {
		this.employnum = employnum;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
