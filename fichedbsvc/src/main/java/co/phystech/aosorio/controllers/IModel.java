/**
 * 
 */
package co.phystech.aosorio.controllers;

import java.util.List;
import java.util.UUID;

import co.phystech.aosorio.models.Book;
import co.phystech.aosorio.models.Comment;
import co.phystech.aosorio.models.Fiche;

/**
 * @author AOSORIO
 *
 */
public interface IModel {

	
	UUID addFiche(int id, Book book, List<Comment> comments);
	
	UUID addBook(String title, 
			String subTitle, 
			String author, 
			int yearPub, 
			String editor, 
			String collection, 
			int pages,
			String language);

	UUID addComment(UUID bookUuid, 
			String author, 
			String aboutAuthor, 
			String aboutGenre, 
			String aboutCadre, 
			String aboutCharacters, 
			String resume, 
			String extrait,
			String appreciation, 
			boolean isCompleted);
	
	Fiche getFiche(int id, UUID uuid);
	
	List<Fiche> getAllFiches();
	
	List<Book> getAllBooks();

	List<Comment> getAllComments();
	
	List<Comment> getAllCommentsOn(UUID book);
		
	boolean existBook(UUID book);
	
	boolean deleteAll();
	
	boolean deleteFiche(UUID uuid);

}
