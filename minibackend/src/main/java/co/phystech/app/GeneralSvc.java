/**
 * 
 */
package co.phystech.app;

import com.google.gson.Gson;

import spark.ResponseTransformer;

/**
 * @author AOSORIO
 *
 */
public class GeneralSvc {
	
	public static String dataToJson(Object data) {
		
		return new Gson().toJson(data);

	}
	
	public static ResponseTransformer json() {

		return GeneralSvc::dataToJson;
		
	}

}
