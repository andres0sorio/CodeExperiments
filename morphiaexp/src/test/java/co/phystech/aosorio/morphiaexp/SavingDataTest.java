package co.phystech.aosorio.morphiaexp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mongodb.morphia.Datastore;

public class SavingDataTest {

	@Test
	public void simpleTest() {
		
		DbController dbcontroller = DbController.getInstance();

		final Datastore datastore = dbcontroller.getDatabase();

		Project prj = new Project("IN", "Experimento con Morphia");
					
		long nbefore = datastore.getCount(prj);
		
		datastore.save(prj);
		
		long nafter = datastore.getCount(prj);
		
		assertEquals(1, nafter-nbefore);

	}
	
	@Test
	public void fromFileTest() {
			
		assertTrue(true);
		
	}
	
}
