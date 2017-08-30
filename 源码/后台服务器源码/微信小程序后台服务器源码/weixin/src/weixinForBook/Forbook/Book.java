package weixinForBook.Forbook;

public class Book implements Cloneable{
	private String bookname;
	private String synopsis;//simpintroduce
	private String printingtime;//printingtime
	private String folio;//booksize
	private String ISBN;//
	private String category;//category
	private String fahtercategory;//pcategory
	private String img;//img 地址
	private String collectionofbooks;//藏书量
	private String NumberOfCopiesAvailable;//可借副本数量
	private String callnumber;//索书号
	private String author;
	private String publisher;
	private String collectionplace;
	private String id;
	private String date;
	private String date2;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCollectionplace() {
		return collectionplace;
	}
	public void setCollectionplace(String collectionplace) {
		this.collectionplace = collectionplace;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getPrintingtime() {
		return printingtime;
	}
	public void setPrintingtime(String printingtime) {
		this.printingtime = printingtime;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFahtercategory() {
		return fahtercategory;
	}
	public void setFahtercategory(String fahtercategory) {
		this.fahtercategory = fahtercategory;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCollectionofbooks() {
		return collectionofbooks;
	}
	public void setCollectionofbooks(String collectionofbooks) {
		this.collectionofbooks = collectionofbooks;
	}
	public String getNumberOfCopiesAvailable() {
		return NumberOfCopiesAvailable;
	}
	public void setNumberOfCopiesAvailable(String numberOfCopiesAvailable) {
		NumberOfCopiesAvailable = numberOfCopiesAvailable;
	}
}
