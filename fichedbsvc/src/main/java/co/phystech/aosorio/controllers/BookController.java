/**
 * 
 */
package co.phystech.aosorio.controllers;

import java.io.IOException;
import java.util.UUID;

import org.sql2o.Sql2o;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.phystech.aosorio.config.Constants;
import co.phystech.aosorio.models.NewBookPayload;
import spark.Request;
import spark.Response;

/**
 * @author AOSORIO
 *
 */
public class BookController {
	
	public static Object createBook(Request pRequest, Response pResponse) {
		
		Sql2o sql2o = SqlController.getInstance().getAccess();
		
		IModel model = new Sql2oModel(sql2o);
				
		try {
			ObjectMapper mapper = new ObjectMapper();
			NewBookPayload creation = mapper.readValue(pRequest.body(), NewBookPayload.class);
			if (!creation.isValid()) {
				pResponse.status(Constants.HTTP_BAD_REQUEST);
				return "";
			}

			UUID id = model.addBook(creation.getTitle(), 
					creation.getSubTitle(), 
					creation.getAuthor(),
					creation.getYearPub(), 
					creation.getEditor(), 
					creation.getCollection(), 
					creation.getPages(), 
					creation.getLanguage());
			
			pResponse.status(200);
			pResponse.type("application/json");
			return id;
		} catch (IOException jpe) {
			pResponse.status(Constants.HTTP_BAD_REQUEST);
			return "";
		}
				
	}
	
	public static Object readBooks(Request pRequest, Response pResponse) {
		
		Sql2o sql2o = SqlController.getInstance().getAccess();

		IModel model = new Sql2oModel(sql2o);
		
		pResponse.status(200);
		pResponse.type("application/json");
		return model.getAllBooks();
		
		
	}

}
