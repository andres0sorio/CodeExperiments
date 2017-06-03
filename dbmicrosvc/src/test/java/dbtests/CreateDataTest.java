/**
 * 
 */
package dbtests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mongodb.morphia.Datastore;

import controllers.DbController;
import models.Project;

/**
 * @author AOSORIO
 *
 */
public class CreateDataTest {

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
	public void settersTest() {
		
		DbController dbcontroller = DbController.getInstance();

		final Datastore datastore = dbcontroller.getDatabase();

		Project prj = new Project();

		prj.setType("Llave en Mano");
		prj.setName("Planta de acido sulfurico");
		prj.setActor("Industrias basicas de Caldas IBC");
		prj.setActorType("cliente");
		prj.setLocation("Manizales,CO");
		prj.setYear(1990);
		prj.setFirm("Conpancol");
		prj.setDivision("Engineering");
		
		List<String> responsabilties = new ArrayList<String>();
		responsabilties.add("Diseño básico (Proceso y Balances, Diagramas de flujo, P & rsy Distribución  en planta)");
		responsabilties.add("Ingeniería de Detalle (Civil, Mecánica, Eléctrica, Instrumentos)");
				
		prj.setResponsabilities(responsabilties);
		
		long nbefore = datastore.getCount(prj);
		
		datastore.save(prj);
		
		long nafter = datastore.getCount(prj);
		
		assertEquals(1, nafter-nbefore);
	}
	

}
