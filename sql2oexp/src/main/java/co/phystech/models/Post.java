package co.phystech.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

//@Data
public class Post {
	
	private UUID post_uuid;
	private String title;
	private String content;
	private Date publishing_date;
	private List<String> categories;
	/**
	 * @return the post_uuid
	 */
	public UUID getPost_uuid() {
		return post_uuid;
	}
	/**
	 * @param post_uuid the post_uuid to set
	 */
	public void setPost_uuid(UUID post_uuid) {
		this.post_uuid = post_uuid;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the publishing_date
	 */
	public Date getPublishing_date() {
		return publishing_date;
	}
	/**
	 * @param publishing_date the publishing_date to set
	 */
	public void setPublishing_date(Date publishing_date) {
		this.publishing_date = publishing_date;
	}
	/**
	 * @return the categories
	 */
	public List<String> getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	
}
