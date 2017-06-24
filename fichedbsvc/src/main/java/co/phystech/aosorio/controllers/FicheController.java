/**
 * 
 */
package co.phystech.aosorio.controllers;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.phystech.aosorio.config.Constants;
import co.phystech.aosorio.models.BackendMessage;
import co.phystech.aosorio.models.NewFichePayload;
import spark.Request;
import spark.Response;

/**
 * @author AOSORIO
 *
 */
public class FicheController {

	private final static Logger slf4jLogger = LoggerFactory.getLogger(FicheController.class);

	public static Object createFiche(Request pRequest, Response pResponse) {

		Sql2o sql2o = SqlController.getInstance().getAccess();

		BackendMessage returnMessage = new BackendMessage();

		try {

			ObjectMapper mapper = new ObjectMapper();

			IModel model = new Sql2oModel(sql2o);

			slf4jLogger.info(pRequest.body());

			NewFichePayload newFiche = mapper.readValue(pRequest.body(), NewFichePayload.class);

			if (!newFiche.isValid()) {
				slf4jLogger.info("Invalid body object");
				pResponse.status(Constants.HTTP_BAD_REQUEST);
				return returnMessage.getNotOkMessage("Invalid body object");
			}

			slf4jLogger.info(newFiche.toString());

			UUID id = model.addFiche(newFiche.getId(), newFiche.getBook(), newFiche.getComments());

			pResponse.status(200);
			pResponse.type("application/json");

			return returnMessage.getOkMessage(String.valueOf(id));

		} catch (IOException jpe) {
			slf4jLogger.debug("Problem adding fiche");
			pResponse.status(Constants.HTTP_BAD_REQUEST);
			return returnMessage.getNotOkMessage("Problem adding fiche");
		}

	}

	public static Object readFiches(Request pRequest, Response pResponse) {

		Sql2o sql2o = SqlController.getInstance().getAccess();

		IModel model = new Sql2oModel(sql2o);

		pResponse.status(200);
		pResponse.type("application/json");
		return model.getAllFiches();

	}

	public static Object deleteAll(Request pRequest, Response pResponse) {

		Sql2o sql2o = SqlController.getInstance().getAccess();

		BackendMessage returnMessage = new BackendMessage();

		IModel model = new Sql2oModel(sql2o);
		slf4jLogger.info(pRequest.body());
		boolean status = model.deleteAll();

		pResponse.status(200);
		pResponse.type("application/json");

		return returnMessage.getOkMessage(String.valueOf(status));

	}

}
