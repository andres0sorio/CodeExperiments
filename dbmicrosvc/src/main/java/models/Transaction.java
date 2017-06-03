/**
 * 
 */
package models;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * @author AOSORIO
 *
 */
@Entity("transactions")
public class Transaction extends Document {
	
	@Id
    private ObjectId id;
	@Embedded
	private List<String> items;
	@Property
	private String actorType;
	
	public Transaction() {
		super();
	}
	
	/**
	 * @param items
	 */
	public Transaction(List<String> items) {
		super();
		this.items = items;
	}

	/**
	 * @return the items
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<String> items) {
		this.items = items;
	}
	


}
