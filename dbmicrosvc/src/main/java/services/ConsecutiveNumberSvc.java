/**
 * 
 */
package services;

import org.mongodb.morphia.Datastore;

import controllers.DbController;

/**
 * @author AOSORIO
 *
 */
public class ConsecutiveNumberSvc {

	private static ConsecutiveNumberSvc instance = null;
	@SuppressWarnings("unused")
	private static Datastore datastore = null;
	
	protected ConsecutiveNumberSvc () {
		
		DbController dbcontroller = DbController.getInstance();
		datastore = dbcontroller.getDatabase();
				
	}
	
	public static ConsecutiveNumberSvc getInstance() {
		if (instance == null) {
			instance = new ConsecutiveNumberSvc();
		}
		return instance;
	}
	
	public static int GetConsecutiveNumber(String pCollection) {
		
		//datastore.
		
		
		return 0;
		
	}
	
}
