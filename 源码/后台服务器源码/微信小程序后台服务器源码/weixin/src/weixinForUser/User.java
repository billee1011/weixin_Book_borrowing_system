package weixinForUser;

public class User {
	private String phonenumber=null;
	private String idcard=null;
	private String name=null;
	private String password=null;
	private String weixin=null;
	private String email=null;
	private String setting1=null;
	public User(String phonenumber, String idcard, String name, String password, String weixin, String email,
			String setting1, String setting2, String setting3) {
		super();
		this.phonenumber = phonenumber;
		this.idcard = idcard;
		this.name = name;
		this.password = password;
		this.weixin = weixin;
		this.email = email;
		this.setting1 = setting1;
		this.setting2 = setting2;
		this.setting3 = setting3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSetting1() {
		return setting1;
	}
	public void setSetting1(String setting1) {
		this.setting1 = setting1;
	}
	public String getSetting2() {
		return setting2;
	}
	public void setSetting2(String setting2) {
		this.setting2 = setting2;
	}
	public String getSetting3() {
		return setting3;
	}
	public void setSetting3(String setting3) {
		this.setting3 = setting3;
	}

	private String setting2=null;
	private String setting3=null;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String phonenumber, String idcard, String name, String password, String weixin) {
		super();
		this.phonenumber = phonenumber;
		this.idcard = idcard;
		this.name = name;
		this.password = password;
		this.weixin = weixin;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWeixin() {
		return weixin;
	}
	
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
} 
