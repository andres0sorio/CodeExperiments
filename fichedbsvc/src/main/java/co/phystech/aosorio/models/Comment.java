/**
 * 
 */
package co.phystech.aosorio.models;

import java.util.Date;
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
	
	private Date submission_date;

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

	public Date getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(Date submission_date) {
		this.submission_date = submission_date;
	}
	
	
}
