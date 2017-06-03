/**
 * 
 */
package co.phystech.aosorio.app;

import static spark.Spark.get;
import static spark.Spark.post;

import co.phystech.aosorio.controllers.BookController;
import co.phystech.aosorio.services.GeneralSvc;

/**
 * @author AOSORIO
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
	
		post("/books", BookController::createBook, GeneralSvc.json());

		get("/books", BookController::readBooks, GeneralSvc.json());
		
		//get("/fiches/:uuid")
		
		//post("/posts/:uuid/comments");
		
		//get("/posts/:uuid/comments");
	
				
	}

}
