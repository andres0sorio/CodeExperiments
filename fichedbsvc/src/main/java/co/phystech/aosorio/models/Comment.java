/**
 * 
 */
package co.phystech.aosorio.models;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author AOSORIO
 *
 */
public class Comment {
	
	UUID comment_uuid;
	UUID book_uuid;
	private String author;
	private String aboutAuthor;
	private String aboutGenre;
	private String aboutCadre;
	private String aboutCharacters;
	
	private String resume;
	private String extrait;
	private String appreciation;
	
	private boolean isCompleted;
	private Timestamp submission_date;
	private Timestamp completion_date;

	public UUID getComment_uuid() {
		return comment_uuid;
	}

	public void setComment_uuid(UUID comment_uuid) {
		this.comment_uuid = comment_uuid;
	}

	public UUID getBook_uuid() {
		return book_uuid;
	}

	public void setBook_uuid(UUID book_uuid) {
		this.book_uuid = book_uuid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAboutAuthor() {
		return aboutAuthor;
	}

	public void setAboutAuthor(String aboutAuthor) {
		this.aboutAuthor = aboutAuthor;
	}

	public String getAboutGenre() {
		return aboutGenre;
	}

	public void setAboutGenre(String aboutGenre) {
		this.aboutGenre = aboutGenre;
	}

	public String getAboutCadre() {
		return aboutCadre;
	}

	public void setAboutCadre(String aboutCadre) {
		this.aboutCadre = aboutCadre;
	}

	public String getAboutCharacters() {
		return aboutCharacters;
	}

	public void setAboutCharacters(String aboutCharacters) {
		this.aboutCharacters = aboutCharacters;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getExtrait() {
		return extrait;
	}

	public void setExtrait(String extrait) {
		this.extrait = extrait;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public Timestamp getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(Timestamp submission_date) {
		this.submission_date = submission_date;
	}

	/**
	 * @return the isCompleted
	 */
	public boolean getIsCompleted() {
		return isCompleted;
	}

	/**
	 * @param isCompleted the isCompleted to set
	 */
	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	/**
	 * @return the completion_date
	 */
	public Timestamp getCompletion_date() {
		return completion_date;
	}

	/**
	 * @param completion_date the completion_date to set
	 */
	public void setCompletion_date(Timestamp completion_date) {
		this.completion_date = completion_date;
	}
	
	
}