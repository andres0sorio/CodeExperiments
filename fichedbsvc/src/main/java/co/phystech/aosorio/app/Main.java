/**
 * 
 */
package co.phystech.aosorio.app;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.delete;


import co.phystech.aosorio.controllers.BookController;
import co.phystech.aosorio.controllers.CommentController;
import co.phystech.aosorio.controllers.FicheController;
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

		port(getHerokuAssignedPort());

		CorsFilter.apply();
		
		get("/hello", (req, res) -> "Fiche DB service deployed");

		// Fiches
		
		post(Routes.FICHES, FicheController::createFiche, GeneralSvc.json());

		get(Routes.FICHES, FicheController::readFiches, GeneralSvc.json());
		
		delete(Routes.FICHES + "all", FicheController::deleteAll, GeneralSvc.json());
		
		delete(Routes.FICHES + ":uuid", FicheController::deleteFiche, GeneralSvc.json());

		// Books
		
		post(Routes.BOOKS, BookController::createBook, GeneralSvc.json());

		get(Routes.BOOKS, BookController::readBooks, GeneralSvc.json());

		// Comments
		
		post(Routes.COMMENTS, CommentController::createComment, GeneralSvc.json());

		get(Routes.COMMENTS, CommentController::readComments, GeneralSvc.json());

		// Statistics
		
		get("/statistics", StatisticsSvc::getBasicStats, GeneralSvc.json());
		
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

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567;
	}

}
