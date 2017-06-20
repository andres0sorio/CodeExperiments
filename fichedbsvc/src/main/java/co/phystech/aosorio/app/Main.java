/**
 * 
 */
package co.phystech.aosorio.app;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

import co.phystech.aosorio.controllers.BookController;
import co.phystech.aosorio.services.GeneralSvc;
import co.phystech.aosorio.services.StatisticsSvc;
import co.phystech.aosorio.config.CorsFilter;
import co.phystech.aosorio.config.Routes;

/**
 * @author AOSORIO
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		CorsFilter.apply();
		
		post(Routes.FICHES, BookController::createBook, GeneralSvc.json());

		get(Routes.FICHES, BookController::readBooks, GeneralSvc.json());
		
		post(Routes.BOOKS, BookController::createBook, GeneralSvc.json());

		get(Routes.BOOKS, BookController::readBooks, GeneralSvc.json());
		
		post(Routes.COMMENTS, BookController::createBook, GeneralSvc.json());

		get(Routes.COMMENTS, BookController::readBooks, GeneralSvc.json());
		
		get("/statistics", StatisticsSvc::getBasicStats, GeneralSvc.json());
		
		//get("/fiches/:uuid")		
		//post("/posts/:uuid/comments");		
		//get("/posts/:uuid/comments");
	
		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}
			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}
			return "OK";
		});
		
	}

}
