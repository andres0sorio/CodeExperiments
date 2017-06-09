package co.phystech.app;

public class NestedTestObject {
	
	private String title;
	private String subTitle;
	private String author;
	private int yearPub;
	private String editor;
	private String collection;
	private int pages;
	private String language;	
	/**
	 * @param title
	 * @param author
	 */
	public NestedTestObject(String title, String author) {
		super();
		this.title = title;
		this.author = author;
		this.setSubTitle("A subtitle");
		this.setCollection("A Collection");
		this.setEditor("A Editor");
		this.setLanguage("English");
		this.setYearPub(2017);
		this.setPages(100);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public int getYearPub() {
		return yearPub;
	}
	public void setYearPub(int yearPub) {
		this.yearPub = yearPub;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}


}
