package co.phystech.models;

import java.util.Date;
import java.util.UUID;

//@Data
public class Comment {
	
	UUID comment_uuid;
	UUID post_uuid;
	String author;
	String content;
	boolean approved;
	Date submission_date;
	/**
	 * @return the comment_uuid
	 */
	public UUID getComment_uuid() {
		return comment_uuid;
	}
	/**
	 * @param comment_uuid the comment_uuid to set
	 */
	public void setComment_uuid(UUID comment_uuid) {
		this.comment_uuid = comment_uuid;
	}
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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
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
	 * @return the approved
	 */
	public boolean isApproved() {
		return approved;
	}
	/**
	 * @param approved the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	/**
	 * @return the submission_date
	 */
	public Date getSubmission_date() {
		return submission_date;
	}
	/**
	 * @param submission_date the submission_date to set
	 */
	public void setSubmission_date(Date submission_date) {
		this.submission_date = submission_date;
	}
	
	
}
