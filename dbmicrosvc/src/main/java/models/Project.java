/**
 * 
 */
package models;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

/**
 * @author AOSORIO
 *
 */
@Entity("projects")
public class Project extends Document {
	
	@Id
    private ObjectId id;
	
	@Property
	private String name;
	@Property
    private String location;
	@Property
    private int budget;
	@Property
	private String actorType;
	@Embedded
    private List<String> responsabilities;
	
    public Project() {
    	
    }
    
    public Project(String pType, String pName) {
    	
    	this.setType(pType);
    	this.setName(pName);
    	
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the budget
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * @return the actorType
	 */
	public String getActorType() {
		return actorType;
	}

	/**
	 * @param actorType the actorType to set
	 */
	public void setActorType(String actorType) {
		this.actorType = actorType;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	 * @return the responsabilities
	 */
	public List<String> getResponsabilities() {
		return responsabilities;
	}

	/**
	 * @param responsabilities the responsabilities to set
	 */
	public void setResponsabilities(List<String> responsabilities) {
		this.responsabilities = responsabilities;
	}
    
    
    
	
}
