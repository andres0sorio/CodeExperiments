package co.phystech.app;

import static spark.Spark.*;

import co.phystech.app.BackendController;
import co.phystech.app.GeneralSvc;

public class Main {
    
	public static void main(String[] args) {
        
		CorsFilter.apply();
		
		get("/objects", BackendController::readObject, GeneralSvc.json());
        
        put("/objects", BackendController::createObject, GeneralSvc.json());
        
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
