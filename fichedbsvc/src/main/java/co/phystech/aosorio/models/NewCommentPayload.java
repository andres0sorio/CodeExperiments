package co.phystech.aosorio.models;

import java.util.UUID;

public class NewCommentPayload implements IValidable {

	UUID book_uuid;
	
	private String author;
	private String aboutAuthor;
	private String aboutGenre;
	private String aboutCadre;
	private String aboutCharacters;
	
	private String resume;
	private String extrait;
	private String appreciation;
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
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

	public UUID getBook_uuid() {
		return book_uuid;
	}

	public void setBook_uuid(UUID book_uuid) {
		this.book_uuid = book_uuid;
	}

	
	
}
