/**
 * 
 */
package co.phystech.aosorio.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import co.phystech.aosorio.models.Book;
import co.phystech.aosorio.models.Comment;
import co.phystech.aosorio.services.IUuidGenerator;
import co.phystech.aosorio.services.RandomUuidGenerator;

/**
 * @author AOSORIO
 *
 */
public class Sql2oModel implements IModel {

	private Sql2o sql2o;
	private IUuidGenerator uuidGenerator;

	public Sql2oModel(Sql2o sql2o) {
		this.sql2o = sql2o;
		uuidGenerator = new RandomUuidGenerator();
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
			String aboutCharacters, String resume, String extrait, String appreciation) {

		try (Connection conn = sql2o.open()) {
			UUID commentUuid = uuidGenerator.generate();
			conn.createQuery(
					"insert into comments(comment_uuid, book_uuid, author, aboutauthor, aboutgenre, aboutcadre, aboutcharacters, resume, extrait, appreciation, submission_date) VALUES (:comment_uuid, :book_uuid, :author, :aboutauthor, :aboutgenre, :aboutcadre, :aboutcharacters, :resume, :extrait, :appreciation, :submission_date)")
					.addParameter("comment_uuid", commentUuid)
					.addParameter("book_uuid", bookUuid)
					.addParameter("author", author)
					.addParameter("aboutauthor", aboutAuthor)
					.addParameter("aboutgenre", aboutGenre)
					.addParameter("aboutcadre", aboutCadre)
					.addParameter("aboutcharacters", aboutCharacters)
					.addParameter("resume", resume)
					.addParameter("extrait", extrait)
					.addParameter("appreciation", appreciation)
					.addParameter("submission_date", new Date()).executeUpdate();
			return commentUuid;
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
			List<Book> books = conn.createQuery("select * from books where book_uuid=:book_uuid").addParameter("book_uuid", book)
					.executeAndFetch(Book.class);
			return books.size() > 0;
		}
	}

}
