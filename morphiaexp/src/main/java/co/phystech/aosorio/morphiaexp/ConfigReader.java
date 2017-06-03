/**
 * 
 */
package co.phystech.aosorio.morphiaexp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mongodb.ServerAddress;

/**
 * @author AOSORIO
 *
 */
public class ConfigReader {
	
	private static String dbServerUrl;
	private static String dbPort;
	private static String dbName;
	private static String dbEnv;
	private static String[] dbReplicaSetIPs;
	private static List<ServerAddress> dbServerAdresses;
	
	public ConfigReader(String pConfig) {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(pConfig);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			dbEnv = prop.getProperty("mongo.env");
			dbServerUrl = prop.getProperty("mongo.url");
			dbPort = prop.getProperty("mongo.port");
			dbName = prop.getProperty("mongo.db.name");			
			dbReplicaSetIPs = prop.getProperty("mongo.db.replicasetips").split(",");
					
			dbServerAdresses = new ArrayList<ServerAddress>();
			
			for( String ips : dbReplicaSetIPs) {
				dbServerAdresses.add( new ServerAddress(ips, 27017) );
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
			
			dbEnv = "local";
			dbServerUrl = "localhost";
			dbPort = "27017";
			dbName = "factory";

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the dbServerUrl
	 */
	public static String getDbServerUrl() {
		return dbServerUrl;
	}

	/**
	 * @param dbServerUrl the dbServerUrl to set
	 */
	public static void setDbServerUrl(String dbServerUrl) {
		ConfigReader.dbServerUrl = dbServerUrl;
	}

	/**
	 * @return the dbPort
	 */
	public static String getDbPort() {
		return dbPort;
	}

	/**
	 * @param dbPort the dbPort to set
	 */
	public static void setDbPort(String dbPort) {
		ConfigReader.dbPort = dbPort;
	}

	/**
	 * @return the dbName
	 */
	public static String getDbName() {
		return dbName;
	}

	/**
	 * @param dbName the dbName to set
	 */
	public static void setDbName(String dbName) {
		ConfigReader.dbName = dbName;
	}

	/**
	 * @return the dbEnv
	 */
	public static String getDbEnv() {
		return dbEnv;
	}

	/**
	 * @param dbEnv the dbEnv to set
	 */
	public static void setDbEnv(String dbEnv) {
		ConfigReader.dbEnv = dbEnv;
	}

	/**
	 * @return the dbReplicaSetIPs
	 */
	public static String[] getDbReplicaSetIPs() {
		return dbReplicaSetIPs;
	}

	/**
	 * @param dbReplicaSetIPs the dbReplicaSetIPs to set
	 */
	public static void setDbReplicaSetIPs(String[] dbReplicaSetIPs) {
		ConfigReader.dbReplicaSetIPs = dbReplicaSetIPs;
	}

	/**
	 * @return the dbServerAdresses
	 */
	public static List<ServerAddress> getDbServerAdresses() {
		return dbServerAdresses;
	}

	/**
	 * @param dbServerAdresses the dbServerAdresses to set
	 */
	public static void setDbServerAdresses(List<ServerAddress> dbServerAdresses) {
		ConfigReader.dbServerAdresses = dbServerAdresses;
	}

	
}
