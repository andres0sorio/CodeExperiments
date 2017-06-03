package fichedbsvc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import co.phystech.aosorio.controllers.*;
import co.phystech.aosorio.models.*;

public class ModelTests {

	@Test
	public void bookCreationTest() {
		String address = new String("jdbc:postgresql://localhost:5432/fichedb");
		String dbUsername = new String("aosorio");
		String dbPassword = new String("sparkforthewin");

		Sql2o sql2o = new Sql2o(address, dbUsername, dbPassword, new PostgresQuirks() {
			{
				// make sure we use default UUID converter.
				converters.put(UUID.class, new UUIDConverter());
			}
		});

		IModel model = new Sql2oModel(sql2o);

		NewBookPayload creation = new NewBookPayload();

		String title = "The Phoenix Project";
		String subTitle = "A Novel about IT, Devops";
		String author = "Gene Kim, Kevin Behr, George Spafford";
		int yearPub = 2013;
		String editor = "IT Revolution Press";
		String collection = "Business";
		int pages = 312;
		String language = "English";

		creation.setTitle(title);
		creation.setSubTitle(subTitle);
		creation.setAuthor(author);
		creation.setYearPub(yearPub);
		creation.setEditor(editor);
		creation.setCollection(collection);
		creation.setPages(pages);
		creation.setLanguage(language);

		UUID id = model.addBook(creation.getTitle(), 
				creation.getSubTitle(), 
				creation.getAuthor(),
				creation.getYearPub(), 
				creation.getEditor(), 
				creation.getCollection(), 
				creation.getPages(), 
				creation.getLanguage());

		List<Book> books = new ArrayList<Book>();
		books = model.getAllBooks();

		Book lastBook = books.get(books.size() - 1);

		// System.out.println(id);
		// System.out.println(posts.size());
		// System.out.println(lastPost.getPost_uuid());

		assertEquals(id, lastBook.getBook_uuid());

	}

	@Test
	public void commentCreationTest() {

		String address = new String("jdbc:postgresql://localhost:5432/fichedb");
		String dbUsername = new String("aosorio");
		String dbPassword = new String("sparkforthewin");

		Sql2o sql2o = new Sql2o(address, dbUsername, dbPassword, new PostgresQuirks() {
			{
				// make sure we use default UUID converter.
				converters.put(UUID.class, new UUIDConverter());
			}
		});

		IModel model = new Sql2oModel(sql2o);

		NewCommentPayload creation = new NewCommentPayload();

		String author = "Andres Osorio";
		String aboutAuthor = "Gen Kim and team are experts in DevOps";
		String aboutGenre = "Similar type of novel as the Goal";
		String aboutCadre = "Important IT business, link between success and DevOps";
		String aboutCharacters = "Main character Bill";
		String resume = "Bill is asked to take charge of IT operations and improve the IT department";
		String extrait = "DevOps";
		String appreciation = "Great novel; very interesting";

		creation.setAuthor(author);
		creation.setAboutAuthor(aboutAuthor);
		creation.setAboutGenre(aboutGenre);
		creation.setAboutCadre(aboutCadre);
		creation.setAboutCharacters(aboutCharacters);
		creation.setResume(resume);
		creation.setExtrait(extrait);
		creation.setAppreciation(appreciation);
		
		List<Book> books = new ArrayList<Book>();
		books = model.getAllBooks();

		UUID bookUuid = null;
		
		for (Iterator<Book> it = books.iterator(); it.hasNext();) {
			Book abook = it.next();
			if( abook.getTitle().equals("The Phoenix Project") ) {
				bookUuid = abook.getBook_uuid();
				break;
			}
		}
			
		creation.setBook_uuid(bookUuid);
		
		UUID id = model.addComment(creation.getBook_uuid(), 
				creation.getAuthor(), 
				creation.getAboutAuthor(), 
				creation.getAboutGenre(), 
				creation.getAboutCadre(), 
				creation.getAboutCharacters(), 
				creation.getResume(),
				creation.getExtrait(), 
				creation.getAppreciation());
		
		List<Comment> comments = new ArrayList<Comment>();
		comments = model.getAllCommentsOn(bookUuid);

		Comment lastComment = comments.get(comments.size() - 1);
		
		// System.out.println(id);
		// System.out.println(posts.size());
		// System.out.println(lastPost.getPost_uuid());

		assertEquals(id, lastComment.getComment_uuid());
		
	}

}
