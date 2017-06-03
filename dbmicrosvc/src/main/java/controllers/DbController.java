/**
 * 
 */
package controllers;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;


/**
 * @author AOSORIO
 *
 */
public class DbController {
	
	private static DbController instance = null;
	private static Morphia morphia = null;
	private static Datastore datastore = null;
	
	protected DbController() {
		
		morphia = new Morphia();
		morphia.mapPackage("co.phystech.aosorio.dbmicrosvc");
		datastore = morphia.createDatastore(new MongoClient(), "phystech");
		//datastore.ensureIndexes();
		
	}
	
	public static DbController getInstance() {
		if (instance == null) {
			instance = new DbController();
		}
		return instance;
	}

	public Datastore getDatabase() {
		return datastore;
	}

}
