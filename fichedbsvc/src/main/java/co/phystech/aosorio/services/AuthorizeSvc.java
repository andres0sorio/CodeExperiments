/**
 * 
 */
package co.phystech.aosorio.services;

import static spark.Spark.halt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Request;
import spark.Response;

/**
 * @author AOSORIO
 *
 */
public class AuthorizeSvc {
	
	private final static Logger slf4jLogger = LoggerFactory.getLogger(AuthorizeSvc.class);
	
	public static String authorizeUser(Request pRequest, Response pResponse) {

		String jsonResponse = "";
		StringBuilder result = new StringBuilder();

		String route = "/auth/access/";
		String serverPath = "http://localhost:4568";

		String token = pRequest.headers("Authorization").split(" ")[1];
		
		try {
			URL appUrl = new URL(serverPath + route);

			HttpURLConnection urlConnection = (HttpURLConnection) appUrl.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-type", "application/json");
			urlConnection.setRequestProperty ("Authorization", token);
			urlConnection.setRequestMethod("POST");
						
			int httpResult = urlConnection.getResponseCode();
			String httpMessage = urlConnection.getResponseMessage();

			slf4jLogger.debug( String.valueOf(httpResult) + " " + httpMessage);
			
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

			
		} catch (ConnectException e) {
			slf4jLogger.info("Problem in connection");
			
		} catch (Exception e) {
			halt(401, "Not authorized");
			e.printStackTrace();
			
		}
				
		return "OK";

	}

}
