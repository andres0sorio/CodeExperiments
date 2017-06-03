package co.phystech.app;

import static spark.Spark.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.UUID;

import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import co.phystech.models.IModel;
import co.phystech.models.NewProjectPayload;
import co.phystech.models.Sql2oModel;
import co.phystech.services.Constants;

public class Main {

	public static String dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			return sw.toString();
		} catch (IOException e) {
			throw new RuntimeException("IOException from a StringWriter?");
		}
	}

	public static void main(String[] args) {

		String address = new String("jdbc:postgresql://localhost:5432/blog");
		String dbUsername = new String("aosorio");
		String dbPassword = new String("sparkforthewin");

		Sql2o sql2o = new Sql2o(address, dbUsername, dbPassword, new PostgresQuirks() {
			{
				// make sure we use default UUID converter.
				converters.put(UUID.class, new UUIDConverter());
			}
		});

		IModel model = new Sql2oModel(sql2o);

		post("/posts", (request, response) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				NewProjectPayload creation = mapper.readValue(request.body(), NewProjectPayload.class);
				if (!creation.isValid()) {
					response.status(Constants.HTTP_BAD_REQUEST);
					return "";
				}
				UUID id = model.createPost(creation.getTitle(), creation.getContent(), creation.getCategories());
				response.status(200);
				response.type("application/json");
				return id;
			} catch (JsonParseException jpe) {
				response.status(Constants.HTTP_BAD_REQUEST);
				return "";
			}
		});

		// get all post (using HTTP get method)
		get("/posts", (request, response) -> {
			response.status(200);
			response.type("application/json");
			return dataToJson(model.getAllPosts());
		});

	}

}
