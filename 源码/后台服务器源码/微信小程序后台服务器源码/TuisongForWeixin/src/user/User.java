package user;
import java.util.HashMap;

public class User {
	private String Phonenumber = null;
	private String emain = null;
	private String canjieshou = null;
	private String cantuijian = null;
	private String tuijiantime = null;
	private String sendtime=null;
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getCanjieshou() {
		return canjieshou;
	}
	public void setCanjieshou(String canjieshou) {
		this.canjieshou = canjieshou;
	}

	public String getCantuijian() {
		return cantuijian;
	}

	public void setCantuijian(String cantuijian) {
		this.cantuijian = cantuijian;
	}

	public String getTuijiantime() {
		return tuijiantime;
	}

	public void setTuijiantime(String tuijiantime) {
		this.tuijiantime = tuijiantime;
	}

	private HashMap<String, String> Binfo = new HashMap<>();
	private HashMap<String, String> Cinfo = new HashMap<>();
	private HashMap<String, String> Dinfo = new HashMap<>();
	private HashMap<String, String> Hinfo = new HashMap<>();

	public String getPhonenumber() {
		return Phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		Phonenumber = phonenumber;
	}

	public String getEmain() {
		return emain;
	}

	public void setEmain(String emain) {
		this.emain = emain;
	}

	public HashMap<String, String> getBinfo() {
		return Binfo;
	}

	public void setBinfo(HashMap<String, String> binfo) {
		Binfo = binfo;
	}

	public HashMap<String, String> getCinfo() {
		return Cinfo;
	}

	public void setCinfo(HashMap<String, String> cinfo) {
		Cinfo = cinfo;
	}

	public HashMap<String, String> getDinfo() {
		return Dinfo;
	}

	public void setDinfo(HashMap<String, String> dinfo) {
		Dinfo = dinfo;
	}

	public HashMap<String, String> getHinfo() {
		return Hinfo;
	}

	public void setHinfo(HashMap<String, String> hinfo) {
		Hinfo = hinfo;
	}
}