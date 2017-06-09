/**
 * 
 */
package co.phystech.aosorio.app;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

import co.phystech.aosorio.controllers.BookController;
import co.phystech.aosorio.services.GeneralSvc;
import co.phystech.aosorio.config.CorsFilter;

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
		
		post("/books", BookController::createBook, GeneralSvc.json());

		get("/books", BookController::readBooks, GeneralSvc.json());
		
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
