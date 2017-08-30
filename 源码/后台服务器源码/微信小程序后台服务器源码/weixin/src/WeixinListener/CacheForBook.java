package WeixinListener;

import java.util.ArrayList;

import weixinForBook.Forbook.Book;

public class CacheForBook {
	private int cacheNumber=0;
	private ArrayList<Book> books=null;
	public void addCacheNumber(){
		this.cacheNumber++;
	}
	public void jjjj() {
		this.cacheNumber--;
	}
	public int getCacheNumber() {
		return cacheNumber;
	}
	public void setCacheNumber(int cacheNumber) {
		this.cacheNumber = cacheNumber;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
}
