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
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.phystech.aosorio.app.Main;
import co.phystech.aosorio.config.Routes;
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

		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

}
