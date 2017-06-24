/**
 * 
 */
package fichedbsvc;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.phystech.aosorio.app.Main;
import co.phystech.aosorio.config.Routes;
import co.phystech.aosorio.models.Book;
import co.phystech.aosorio.models.Comment;
import co.phystech.aosorio.models.NewFichePayload;
import spark.Spark;

/**
 * @author AOSORIO
 *
 */
public class ControllerTest {

	private final static Logger slf4jLogger = LoggerFactory.getLogger(ControllerTest.class);

	@BeforeClass
	public static void beforeClass() {
		Main.main(null);
	}

	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

	@Test
	public void readBooksTest() {

		int httpResult = 0;
		String httpMessage = "";
		String jsonResponse = "";
		StringBuilder result = new StringBuilder();

		String route = Routes.BOOKS;
		String serverPath = "http://localhost:4567";

		try {
			URL appUrl = new URL(serverPath + route);

			HttpURLConnection urlConnection = (HttpURLConnection) appUrl.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-type", "application/json");
			urlConnection.setRequestMethod("GET");
			httpResult = urlConnection.getResponseCode();
			httpMessage = urlConnection.getResponseMessage();

			InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(in);

			String text = "";
			while ((text = reader.readLine()) != null) {
				jsonResponse += text;
				result.append(text);
			}

			reader.close();
			in.close();
			urlConnection.disconnect();

			slf4jLogger.info(jsonResponse);
			slf4jLogger.info(result.toString());

			JsonParser parser = new JsonParser();
			JsonArray json = parser.parse(result.toString()).getAsJsonArray();

			slf4jLogger.info(String.valueOf(json.size()));

			assertEquals(200, httpResult);
			assertEquals("OK", httpMessage);
			
		} catch (ConnectException e) {
			slf4jLogger.info("Problem in connection");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

	@Test
	public void createBooksTest() {

		int httpResult = 0;
		String httpMessage = "";
		String jsonResponse = "";
		StringBuilder result = new StringBuilder();

		String route = Routes.BOOKS;
		String serverPath = "http://localhost:4567";

		try {
			URL appUrl = new URL(serverPath + route);

			HttpURLConnection urlConnection = (HttpURLConnection) appUrl.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-type", "application/json");
			urlConnection.setRequestMethod("POST");

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

			slf4jLogger.info("New book object: " + newBook);

			Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
			writer.write(newBook);
			writer.flush();

			httpResult = urlConnection.getResponseCode();
			httpMessage = urlConnection.getResponseMessage();

			slf4jLogger.info(httpMessage);

			InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(in);

			String text = "";
			while ((text = reader.readLine()) != null) {
				jsonResponse += text;
				result.append(text);
			}

			reader.close();
			in.close();

			slf4jLogger.info(jsonResponse);
			slf4jLogger.info(result.toString());

			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(result.toString()).getAsJsonObject();

			slf4jLogger.info(String.valueOf(json.size()));

			assertEquals(200, httpResult);
			assertEquals("OK", httpMessage);

		} catch (ConnectException e) {
			slf4jLogger.info("Problem in connection");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

	@Test
	public void createFicheTest() {

		int httpResult = 0;
		String httpMessage = "";
		String jsonResponse = "";
		StringBuilder result = new StringBuilder();

		String route = Routes.FICHES;
		String serverPath = "http://localhost:4567";

		try {
			URL appUrl = new URL(serverPath + route);

			HttpURLConnection urlConnection = (HttpURLConnection) appUrl.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-type", "application/json");
			urlConnection.setRequestMethod("POST");

			Book book = new Book();
			book.setBook_uuid(new UUID(0, 1));
			book.setTitle(ModelTest.title);
			book.setSubTitle(ModelTest.subTitle);
			book.setAuthor(ModelTest.author);
			book.setYearPub(ModelTest.yearPub);
			book.setEditor(ModelTest.editor);
			book.setCollection(ModelTest.collection);
			book.setPages(ModelTest.pages);
			book.setLanguage(ModelTest.language);

			List<Comment> comments = new ArrayList<Comment>();

			Comment commment = new Comment();

			commment.setAuthor(ModelTest.commentAuthor);
			commment.setAboutAuthor(ModelTest.aboutAuthor);
			commment.setAboutGenre(ModelTest.aboutGenre);
			commment.setAboutCadre(ModelTest.aboutCadre);
			commment.setAboutCharacters(ModelTest.aboutCharacters);
			commment.setResume(ModelTest.resume);
			commment.setExtrait(ModelTest.extrait);
			commment.setAppreciation(ModelTest.appreciation);

			comments.add(commment);

			Gson gson = new GsonBuilder().create();

			NewFichePayload fiche = new NewFichePayload();

			fiche.setId(1);
			fiche.setBook(book);
			fiche.setComments(comments);
			String body = gson.toJson(fiche);

			slf4jLogger.info("New fiche object: " + body);

			Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
			writer.write(body);
			writer.flush();

			httpResult = urlConnection.getResponseCode();
			httpMessage = urlConnection.getResponseMessage();

			slf4jLogger.info(httpMessage);

			InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(in);

			String text = "";
			while ((text = reader.readLine()) != null) {
				jsonResponse += text;
				result.append(text);
			}

			reader.close();
			in.close();

			slf4jLogger.info(jsonResponse);
			slf4jLogger.info(result.toString());

			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(result.toString()).getAsJsonObject();

			slf4jLogger.info(String.valueOf(json.size()));

			assertEquals(200, httpResult);
			assertEquals("OK", httpMessage);

		} catch (ConnectException e) {
			slf4jLogger.info("Problem in connection");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}
	
	@Test
	public void deleteAllTest() {

		int httpResult = 0;
		String httpMessage = "";
		String jsonResponse = "";
		StringBuilder result = new StringBuilder();

		String route = "/delete/all";
		String serverPath = "http://localhost:4567";

		try {
			URL appUrl = new URL(serverPath + route);

			HttpURLConnection urlConnection = (HttpURLConnection) appUrl.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-type", "application/json");
			urlConnection.setRequestMethod("DELETE");
			httpResult = urlConnection.getResponseCode();
			httpMessage = urlConnection.getResponseMessage();

			InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(in);

			String text = "";
			while ((text = reader.readLine()) != null) {
				jsonResponse += text;
				result.append(text);
			}

			reader.close();
			in.close();
			urlConnection.disconnect();

			slf4jLogger.info(jsonResponse);
			slf4jLogger.info(result.toString());

			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(result.toString()).getAsJsonObject();

			slf4jLogger.info(String.valueOf(json.size()));

			assertEquals(200, httpResult);
			assertEquals("OK", httpMessage);
			
		} catch (ConnectException e) {
			slf4jLogger.info("Problem in connection");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

}
