package weixinbusiness.weixinforSelectByicon;

public class Gongjju {
	String jOString;
	public void createjosn(){
		this.jOString="{\"listTitle\":[";
	}
	public void addinfo(String string){
		this.jOString+="\""+string+"\""+",";
	}
	public  String returnJOSN(){
		return this.jOString.substring(0, jOString.length()-1)+"]}";
	}
}
