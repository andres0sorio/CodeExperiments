/**
 * 
 */
package co.phystech.aosorio.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import co.phystech.aosorio.models.Book;
import co.phystech.aosorio.models.Comment;
import co.phystech.aosorio.models.Fiche;
import co.phystech.aosorio.models.NewFichePayload;
import co.phystech.aosorio.services.IUuidGenerator;
import co.phystech.aosorio.services.RandomUuidGenerator;

/**
 * @author AOSORIO
 *
 */
public class Sql2oModel implements IModel {

	private final static Logger slf4jLogger = LoggerFactory.getLogger(Sql2oModel.class);

	private Sql2o sql2o;
	private IUuidGenerator uuidGenerator;

	public Sql2oModel(Sql2o sql2o) {
		this.sql2o = sql2o;
		uuidGenerator = new RandomUuidGenerator();
	}

	@Override
	public UUID addFiche(int id, Book book, List<Comment> comments) {

		UUID bookUuid = addBook(book.getTitle(), book.getSubTitle(), book.getAuthor(), book.getYearPub(),
				book.getEditor(), book.getCollection(), book.getPages(), book.getLanguage());

		Iterator<Comment> commentItr = comments.iterator();

		while (commentItr.hasNext()) {

			Comment current = commentItr.next();
			UUID commentUuid = addComment(bookUuid, current.getAuthor(), 
					current.getAboutAuthor(),
					current.getAboutGenre(), 
					current.getAboutCadre(), 
					current.getAboutCharacters(), 
					current.getResume(),
					current.getExtrait(), 
					current.getAppreciation(), 
					current.getIsCompleted());
			slf4jLogger.debug("Added comment with UUID: " + commentUuid.toString());
		}

		return bookUuid;

	}

	@Override
	public UUID addBook(String title, String subTitle, String author, int yearPub, String editor, String collection,
			int pages, String language) {

		try (Connection conn = sql2o.beginTransaction()) {
			UUID postUuid = uuidGenerator.generate();
			conn.createQuery(
					"insert into books(book_uuid, title, subtitle, author, yearpub, editor, collection, pages, language) VALUES (:book_uuid, :title, :subtitle, :author, :yearpub, :editor, :collection, :pages, :language)")
					.addParameter("book_uuid", postUuid)
					.addParameter("title", title)
					.addParameter("subtitle", subTitle)
					.addParameter("author", author)
					.addParameter("yearpub", yearPub)
					.addParameter("editor", editor)
					.addParameter("collection", collection)
					.addParameter("pages", pages)
					.addParameter("language", language).executeUpdate();
			conn.commit();
			return postUuid;
		}

	}

	@Override
	public UUID addComment(UUID bookUuid, String author, String aboutAuthor, String aboutGenre, String aboutCadre,
			String aboutCharacters, String resume, String extrait, String appreciation, boolean isCompleted) {

		try (Connection conn = sql2o.open()) {
			UUID commentUuid = uuidGenerator.generate();
			conn.createQuery(
					"insert into comments(comment_uuid, book_uuid, author, aboutauthor, aboutgenre, aboutcadre, aboutcharacters, resume, extrait, appreciation, submission_date, iscompleted, completion_date) VALUES (:comment_uuid, :book_uuid, :author, :aboutauthor, :aboutgenre, :aboutcadre, :aboutcharacters, :resume, :extrait, :appreciation, :submission_date, :iscompleted, :completion_date)")
					.addParameter("comment_uuid", commentUuid).addParameter("book_uuid", bookUuid)
					.addParameter("author", author).addParameter("aboutauthor", aboutAuthor)
					.addParameter("aboutgenre", aboutGenre).addParameter("aboutcadre", aboutCadre)
					.addParameter("aboutcharacters", aboutCharacters).addParameter("resume", resume)
					.addParameter("extrait", extrait).addParameter("appreciation", appreciation)
					.addParameter("submission_date", new Date())
					.addParameter("iscompleted", isCompleted)
					.addParameter("completion_date", new Date()).executeUpdate();
			return commentUuid;
		}

	}

	@Override
	public List<Fiche> getAllFiches() {

		List<Fiche> fiches = new ArrayList<Fiche>();

		try (Connection conn = sql2o.open()) {

			List<Book> books = conn.createQuery("select * from books").executeAndFetch(Book.class);
			Iterator<Book> bookItr = books.iterator();

			int id = 1;

			while (bookItr.hasNext()) {

				Book currentBook = bookItr.next();
				Fiche currentFiche = new Fiche();
				currentFiche.setFiche_uuid(currentBook.getBook_uuid());
				currentFiche.setId(id);
				currentFiche.setBook(currentBook);
				currentFiche.setComments( getAllCommentsOn(currentBook.getBook_uuid()));
				id += 1;
				fiches.add(currentFiche);
			}
			return fiches;
		}

	}

	@Override
	public List<Book> getAllBooks() {
		try (Connection conn = sql2o.open()) {
			List<Book> books = conn.createQuery("select * from books").executeAndFetch(Book.class);
			return books;
		}
	}

	@Override
	public List<Comment> getAllCommentsOn(UUID book) {
		try (Connection conn = sql2o.open()) {
			return conn.createQuery("select * from comments where book_uuid=:book_uuid").addParameter("book_uuid", book)
					.executeAndFetch(Comment.class);
		}
	}

	@Override
	public boolean existBook(UUID book) {
		try (Connection conn = sql2o.open()) {
			List<Book> books = conn.createQuery("select * from books where book_uuid=:book_uuid")
					.addParameter("book_uuid", book).executeAndFetch(Book.class);
			return books.size() > 0;
		}
	}

	@Override
	public List<Comment> getAllComments() {
		try (Connection conn = sql2o.open()) {
			List<Comment> books = conn.createQuery("select * from comments").executeAndFetch(Comment.class);
			return books;
		}
	}

	@Override
	public boolean deleteAll() {
		try (Connection conn = sql2o.open()) {
			conn.createQuery("delete from comments").executeUpdate();
			conn.createQuery("delete from books").executeUpdate();
			return true;
		}
	}

	@Override
	public boolean deleteFiche(UUID uuid) {
		try (Connection conn = sql2o.open()) {
			conn.createQuery("delete from comments where book_uuid=:book_uuid").addParameter("book_uuid", uuid)
					.executeUpdate();
			conn.createQuery("delete from books where book_uuid=:book_uuid").addParameter("book_uuid", uuid)
			.executeUpdate();
			return true;
		}
	}

	@Override
	public Fiche getFiche(int id, UUID uuid) {

		try (Connection conn = sql2o.open()) {
			List<Book> bookSearch = conn.createQuery("select * from books where book_uuid=:book_uuid")
					.addParameter("book_uuid", uuid).executeAndFetch(Book.class);
			
			Fiche currentFiche = new Fiche();
			currentFiche.setId(id);
			currentFiche.setFiche_uuid(bookSearch.get(0).getBook_uuid());
			currentFiche.setBook(bookSearch.get(0));
			currentFiche.setComments(getAllCommentsOn(uuid));
			
			return currentFiche;

		}

	}

	@Override
	public boolean updateFiche(NewFichePayload fiche) {
		
	
		try (Connection conn = sql2o.open()) {
			conn.createQuery("update books set title=:title, subtitle=:subtitle, author=:author, yearpub=:yearpub, editor=:editor, collection=:collection, pages=:pages, language=:language where book_uuid=:book_uuid")
				.addParameter("book_uuid", fiche.getBook().getBook_uuid())
				.addParameter("title", fiche.getBook().getTitle())
				.addParameter("subtitle", fiche.getBook().getSubTitle())
				.addParameter("author", fiche.getBook().getAuthor())
				.addParameter("yearpub", fiche.getBook().getYearPub())
				.addParameter("editor", fiche.getBook().getEditor())
				.addParameter("collection", fiche.getBook().getCollection())
				.addParameter("pages", fiche.getBook().getPages())
				.addParameter("language", fiche.getBook().getLanguage()).executeUpdate();
		} catch ( Exception ex ) {
			ex.printStackTrace();
			slf4jLogger.info("Problem with DB connection");
		}
				
		return true;
	}
	
}
