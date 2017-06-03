/**
 * 
 */
package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * @author AOSORIO
 *
 */
@Entity("counters")
public class Counter {

	@Id
	private String _id;
	@Property
	private int seq;

}
