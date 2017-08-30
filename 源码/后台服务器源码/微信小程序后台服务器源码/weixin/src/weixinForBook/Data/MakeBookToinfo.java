package weixinForBook.Data;

import sun.text.resources.cldr.om.FormatData_om;
import weixinForBook.Forbook.Book;

public class MakeBookToinfo {
	private String infoJOSN = null;
	private int what;
	private String bookfather;
	public String getBookfather() {
		return bookfather;
	}

	public void setBookfather(String bookfather) {
		this.bookfather = bookfather;
	}

	public MakeBookToinfo(int what) {
		this.what = what;
		switch (what) {
		case 1:// 传递简单的字符串
			infoJOSN = "{\"bookNameList\"" + ":[";
			break;
		case 2:// 传递书籍simpintroduce
			infoJOSN = "{\"simplebookinfo\"" + ":[";
			break;
		case 3:// 传递数据详情
			infoJOSN = "{\"book\"" + ":";
			break;
		case 4:// 传递书籍simpintroduce
			infoJOSN = "{\"simplebookinfo\"" + ":[";
			break;
		case 5:// 传递书籍simpintroduce
			infoJOSN = "{\"simplebookinfo\"" + ":[";
			break;
		default:
			break;
		}
	}

	public void addsimpleteinfo(String string) {
		infoJOSN +=string+",";
	}

	public String createItemString(int what, String... strings) {
		switch (what) {
		case 1:
			return createbooknamelist(strings);
		case 2:
			return createsimplebookinfo(strings);
		case 3:
			return createBook(strings);
		case 4:
			return this.createsimplebookinfo2(strings);
		case 5:
			return this.createsimplebookinfo3(strings);
		default:
			return null;
		}
	}

	public String returnJOSN() {
		switch (this.what) {
		case 1:
			return this.infoJOSN.substring(0, infoJOSN.length() - 1) + "]}";
		case 2:
			return this.infoJOSN.substring(0, infoJOSN.length() - 1) + "]}";
		case 3:
			return this.infoJOSN.substring(0, infoJOSN.length() - 1) + "}";
		case 4:
			return  this.infoJOSN.substring(0, infoJOSN.length() - 1)+ "]}";
		case 5:
			return  this.infoJOSN.substring(0, infoJOSN.length() - 1)+ "]}";
		default:
			return null;
		}
	}
	private String createbooknamelist(String...strings){
		return "\""+strings[0]+"\"";
	}
	private String createsimplebookinfo2(String...strings){
//		书的图片：bookurl
//		bookname：bookname
//		作者：author
//		出版社：press
//		collectionnumber：collectionnumber
//		counterpart量：counterpart
//		isbn
		//version--版本
		int a=0;
		String theInfo="{";
		theInfo+="\"bookname\""+":"+"\""+strings[0]+"\""+",";
		theInfo+="\"bookurl\""+":"+"\""+strings[1]+"\""+",";
		theInfo+="\"author\""+":"+"\""+strings[2]+"\""+",";
		theInfo+="\"press\""+":"+"\""+strings[3]+"\""+",";
		theInfo+="\"collectionnumber\""+":"+"\""+strings[4]+"\""+",";
		theInfo+="\"counterpart\""+":"+"\""+strings[5]+"\""+",";
		theInfo+="\"isbn\""+":"+"\""+strings[6]+"\""+",";
		theInfo+="\"id\""+":"+"\""+strings[7]+"\""+",";
		theInfo+="\"collectionplace\""+":"+"\""+strings[8]+"\""+",";
		theInfo+="\"date\""+":"+"\""+strings[9]+"\""+",";
		//System.out.println(theInfo.substring(0, theInfo.length()-1)+"}");
		return theInfo.substring(0, theInfo.length()-1)+"}";
	}
	private String createsimplebookinfo3(String...strings){
//		书的图片：bookurl
//		bookname：bookname
//		作者：author
//		出版社：press
//		collectionnumber：collectionnumber
//		counterpart量：counterpart
//		isbn
		//version--版本
		int a=0;
		String theInfo="{";
		theInfo+="\"bookname\""+":"+"\""+strings[0]+"\""+",";
		theInfo+="\"bookurl\""+":"+"\""+strings[1]+"\""+",";
		theInfo+="\"author\""+":"+"\""+strings[2]+"\""+",";
		theInfo+="\"press\""+":"+"\""+strings[3]+"\""+",";
		theInfo+="\"collectionnumber\""+":"+"\""+strings[4]+"\""+",";
		theInfo+="\"counterpart\""+":"+"\""+strings[5]+"\""+",";
		theInfo+="\"isbn\""+":"+"\""+strings[6]+"\""+",";
		theInfo+="\"id\""+":"+"\""+strings[7]+"\""+",";
		theInfo+="\"date\""+":"+"\""+strings[8]+"\""+",";
		theInfo+="\"date2\""+":"+"\""+strings[9]+"\""+",";
		//System.out.println(theInfo.substring(0, theInfo.length()-1)+"}");
		return theInfo.substring(0, theInfo.length()-1)+"}";
	}
	private String createsimplebookinfo(String...strings){
//		书的图片：bookurl
//		bookname：bookname
//		作者：author
//		出版社：press
//		collectionnumber：collectionnumber
//		counterpart量：counterpart
//		isbn
		//version--版本
		int a=0;
		String theInfo="{";
		theInfo+="\"bookname\""+":"+"\""+strings[0]+"\""+",";
		theInfo+="\"bookurl\""+":"+"\""+strings[1]+"\""+",";
		theInfo+="\"author\""+":"+"\""+strings[2]+"\""+",";
		theInfo+="\"press\""+":"+"\""+strings[3]+"\""+",";
		theInfo+="\"collectionnumber\""+":"+"\""+strings[4]+"\""+",";
		theInfo+="\"counterpart\""+":"+"\""+strings[5]+"\""+",";
		theInfo+="\"id\""+":"+"\"\""+",";
		theInfo+="\"isbn\""+":"+"\""+strings[6]+"\""+",";
		//System.out.println(theInfo.substring(0, theInfo.length()-1)+"}");
		return theInfo.substring(0, theInfo.length()-1)+"}";
	}
	private String createBook(String...strings){
		String theInfo="{";
		theInfo+="\"bookname\""+":"+"\""+strings[0]+"\""+",";
		theInfo+="\"bookurl\""+":"+"\""+strings[1]+"\""+",";
		theInfo+="\"author\""+":"+"\""+strings[2]+"\""+",";
		theInfo+="\"press\""+":"+"\""+strings[3]+"\""+",";
		theInfo+="\"collectionnumber\""+":"+"\""+strings[4]+"\""+",";
		theInfo+="\"counterpart\""+":"+"\""+strings[5]+"\""+",";
		theInfo+="\"isbn\""+":"+"\""+strings[6]+"\""+",";
		//theInfo+="\"version\""+":"+"\""+strings[7]+"\""+",";
		theInfo+="\"collectionplace\""+":"+"\""+strings[7]+"\""+",";
		theInfo+="\"callnumber\""+":"+"\""+strings[8]+"\""+",";
		theInfo+="\"id\""+":"+strings[9]+",";
		theInfo+="\"category\""+":"+"\""+this.bookfather+"\""+",";
		theInfo+="\"bookcontentinfo\""+":"+"["+getbookcontentinfo(strings)+"]"+"}";
		return theInfo;
	}
	private String getbookcontentinfo(String...strings){
		String info="";
		info+="{"+getItemJOSN("印刷时间",strings[10])+"}"+",";
		info+="{"+getItemJOSN("开本",strings[11])+"}"+",";
		info+="{"+getItemJOSN("简介",strings[12])+"}";
		return info;
	}
	private String getItemJOSN(String title,String context){
		String info="";
		info+="\"title\""+":"+"\""+title+"\""+","+"\"content\""+":"+"\""+context+"\"";
		return info;
	}
}
