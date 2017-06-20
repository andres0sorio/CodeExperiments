package co.phystech.aosorio.controllers;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.phystech.aosorio.config.Constants;
import co.phystech.aosorio.models.BackendMessage;
import co.phystech.aosorio.models.NewCommentPayload;
import spark.Request;
import spark.Response;

public class CommentController {
	
private final static Logger slf4jLogger = LoggerFactory.getLogger(CommentController.class);
	
	public static Object createComment(Request pRequest, Response pResponse) {
		
		Sql2o sql2o = SqlController.getInstance().getAccess();
		
		IModel model = new Sql2oModel(sql2o);
			
		BackendMessage returnMessage = new BackendMessage();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			slf4jLogger.info(pRequest.body());
			
			NewCommentPayload creation = mapper.readValue(pRequest.body(), NewCommentPayload.class);
			
			if (!creation.isValid()) {
				slf4jLogger.info("Invalid body object");
				pResponse.status(Constants.HTTP_BAD_REQUEST);
				return returnMessage.getNotOkMessage("Invalid body object");
			} 

			slf4jLogger.info(creation.toString());
			
			UUID id = model.addComment(creation.getBook_uuid(), 
					creation.getAuthor(), 
					creation.getAboutAuthor(), 
					creation.getAboutGenre(), 
					creation.getAboutCadre(), 
					creation.getAboutCharacters(), 
					creation.getResume(),
					creation.getExtrait(), 
					creation.getAppreciation());
			
			pResponse.status(200);
			pResponse.type("application/json");
			
			return returnMessage.getOkMessage(String.valueOf(id));
			
		} catch (IOException jpe) {
			slf4jLogger.debug("Problem adding comment");
			pResponse.status(Constants.HTTP_BAD_REQUEST);
			return returnMessage.getNotOkMessage("Problem adding comment");
		}
				
	}
	
	public static Object readComments(Request pRequest, Response pResponse) {
		
		Sql2o sql2o = SqlController.getInstance().getAccess();

		IModel model = new Sql2oModel(sql2o);
		
		pResponse.status(200);
		pResponse.type("application/json");
		return model.getAllComments();
			
	}	

}