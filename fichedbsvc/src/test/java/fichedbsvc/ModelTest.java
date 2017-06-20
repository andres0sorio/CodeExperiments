package fichedbsvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import co.phystech.aosorio.controllers.IModel;
import co.phystech.aosorio.controllers.Sql2oModel;
import co.phystech.aosorio.models.Book;
import co.phystech.aosorio.models.Comment;
import co.phystech.aosorio.models.Fiche;
import co.phystech.aosorio.models.NewBookPayload;
import co.phystech.aosorio.models.NewCommentPayload;
import co.phystech.aosorio.models.NewFichePayload;

public class ModelTest {

	private final static Logger slf4jLogger = LoggerFactory.getLogger(ModelTest.class);

	private static final String CONFIG_ADDRESS = "jdbc:postgresql://localhost:5432/fichedb";
	private static final String CONFIG_DBUSER = "aosorio";
	private static final String CONFIG_PASS = "sparkforthewin";

	public static final String title = "The Phoenix Project";
	public static final String subTitle = "A Novel about IT, Devops";
	public static final String author = "Gene Kim, Kevin Behr, George Spafford";
	public static final int yearPub = 2013;
	public static final String editor = "IT Revolution Press";
	public static final String collection = "Business";
	public static final int pages = 312;
	public static final String language = "English";

	public static final String commentAuthor = "Andres Osorio";
	public static final String aboutAuthor = "Gen Kim and team are experts in DevOps";
	public static final String aboutGenre = "Similar type of novel as the Goal";
	public static final String aboutCadre = "Important IT business, link between success and DevOps";
	public static final String aboutCharacters = "Main character Bill";
	public static final String resume = "Bill is asked to take charge of IT operations and improve the IT department";
	public static final String extrait = "DevOps";
	public static final String appreciation = "Great novel; very interesting";

	@Test
	public void bookCreationTest() {

		Sql2o sql2o = new Sql2o(CONFIG_ADDRESS, CONFIG_DBUSER, CONFIG_PASS, new PostgresQuirks() {
			{
				// make sure we use default UUID converter.
				converters.put(UUID.class, new UUIDConverter());
			}
		});

		IModel model = new Sql2oModel(sql2o);

		NewBookPayload creation = new NewBookPayload();

		creation.setTitle(title);
		creation.setSubTitle(subTitle);
		creation.setAuthor(author);
		creation.setYearPub(yearPub);
		creation.setEditor(editor);
		creation.setCollection(collection);
		creation.setPages(pages);
		creation.setLanguage(language);

		UUID id = model.addBook(creation.getTitle(), creation.getSubTitle(), creation.getAuthor(),
				creation.getYearPub(), creation.getEditor(), creation.getCollection(), creation.getPages(),
				creation.getLanguage());

		List<Book> books = new ArrayList<Book>();
		books = model.getAllBooks();

		Book lastBook = books.get(books.size() - 1);

		assertEquals(id, lastBook.getBook_uuid());

	}

	@Test
	public void commentCreationTest() {

		Sql2o sql2o = new Sql2o(CONFIG_ADDRESS, CONFIG_DBUSER, CONFIG_PASS, new PostgresQuirks() {
			{
				// make sure we use default UUID converter.
				converters.put(UUID.class, new UUIDConverter());
			}
		});

		IModel model = new Sql2oModel(sql2o);

		NewCommentPayload creation = new NewCommentPayload();

		creation.setAuthor(commentAuthor);
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
			if (abook.getTitle().equals("The Phoenix Project")) {
				bookUuid = abook.getBook_uuid();
				break;
			}
		}

		creation.setBook_uuid(bookUuid);

		UUID id = model.addComment(creation.getBook_uuid(), creation.getAuthor(), creation.getAboutAuthor(),
				creation.getAboutGenre(), creation.getAboutCadre(), creation.getAboutCharacters(), creation.getResume(),
				creation.getExtrait(), creation.getAppreciation());

		List<Comment> comments = new ArrayList<Comment>();
		comments = model.getAllCommentsOn(bookUuid);

		Comment lastComment = comments.get(comments.size() - 1);

		assertEquals(id, lastComment.getComment_uuid());

	}

	@Test
	public void ficheCreationTest() {

		Sql2o sql2o = new Sql2o(CONFIG_ADDRESS, CONFIG_DBUSER, CONFIG_PASS, new PostgresQuirks() {
			{
				// make sure we use default UUID converter.
				converters.put(UUID.class, new UUIDConverter());
			}
		});

		IModel model = new Sql2oModel(sql2o);

		NewFichePayload fiche = new NewFichePayload();

		Book book = new Book();

		book.setTitle(title);
		book.setSubTitle(subTitle);
		book.setAuthor(author);
		book.setYearPub(yearPub);
		book.setEditor(editor);
		book.setCollection(collection);
		book.setPages(pages);
		book.setLanguage(language);

		List<Comment> comments = new ArrayList<Comment>();
		comments.add(new Comment());

		fiche.setId(1);
		fiche.setBook(book);
		fiche.setComments(comments);

		UUID id = model.addFiche(fiche.getId(), fiche.getBook(), fiche.getComments());

		slf4jLogger.info(id.toString());

		List<Fiche> fiches = new ArrayList<Fiche>();
		fiches = model.getAllFiches();

		Fiche lastFiche = fiches.get(fiches.size() - 1);

		slf4jLogger.info(lastFiche.getBook().getBook_uuid().toString());

		assertEquals(id, lastFiche.getBook().getBook_uuid());

	}

	@Test
	public void bookParsingTest() {

		boolean result = false;

		JsonObject bookJson = new JsonObject();

		bookJson.addProperty("title", ModelTest.title);
		bookJson.addProperty("subTitle", ModelTest.subTitle);
		bookJson.addProperty("author", ModelTest.author);
		bookJson.addProperty("yearPub", ModelTest.yearPub);
		bookJson.addProperty("editor", ModelTest.editor);
		bookJson.addProperty("collection", ModelTest.collection);
		bookJson.addProperty("pages", ModelTest.pages);
		bookJson.addProperty("language", ModelTest.language);

		String newBook = bookJson.toString();

		ObjectMapper mapper = new ObjectMapper();

		NewBookPayload payload;

		try {
			payload = mapper.readValue(newBook, NewBookPayload.class);

			if (!payload.isValid()) {
				slf4jLogger.info("Invalid body object");
			} else {

				slf4jLogger.info(payload.toString());
				result = true;
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertTrue(result);

	}

	@Test
	public void ficheParsingTest() {

		boolean result = false;

		Book book = new Book();
		book.setBook_uuid(new UUID(0, 1));
		book.setTitle(title);
		book.setSubTitle(subTitle);
		book.setAuthor(author);
		book.setYearPub(yearPub);
		book.setEditor(editor);
		book.setCollection(collection);
		book.setPages(pages);
		book.setLanguage(language);

		List<Comment> comments = new ArrayList<Comment>();
		
		Comment commment = new Comment();

		commment.setAuthor(commentAuthor);
		commment.setAboutAuthor(aboutAuthor);
		commment.setAboutGenre(aboutGenre);
		commment.setAboutCadre(aboutCadre);
		commment.setAboutCharacters(aboutCharacters);
		commment.setResume(resume);
		commment.setExtrait(extrait);
		commment.setAppreciation(appreciation);
		
		comments.add(commment);
		
		Gson gson = new GsonBuilder().create();

		NewFichePayload fiche = new NewFichePayload();
		
		fiche.setId(1);
		fiche.setBook(book);
		fiche.setComments(comments);
		String json = gson.toJson(fiche);

		slf4jLogger.info(json);

		ObjectMapper mapper = new ObjectMapper();

		NewFichePayload payload;

		try {
			payload = mapper.readValue(json, NewFichePayload.class);
			if (!payload.isValid()) {
				slf4jLogger.info("Invalid body object");
			} else {

				slf4jLogger.info(payload.toString());
				result = true;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertTrue(result);

	}

}
