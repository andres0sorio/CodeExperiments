package co.phystech.aosorio.morphiaexp;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("projects")
public class Project {
	
	@Id
    private ObjectId id;
	
	private String type;
	private String code;
    private String name;
    private String firm;
    private String division;
	private String client;
    private String location;
    private int budget;
    private List<String> responsabilities;
    
    public Project() {
    	
    }
    
    public Project(String pType, String pName) {
    	
    	this.setType(pType);
    	this.setName(pName);
    	
    }
    
    
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the client
	 */
	public String getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
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
    
    /**
	 * @return the firm
	 */
	public String getFirm() {
		return firm;
	}

	/**
	 * @param firm the firm to set
	 */
	public void setFirm(String firm) {
		this.firm = firm;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}
    
    
}
