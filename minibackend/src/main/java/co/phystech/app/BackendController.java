/**
 * 
 */
package co.phystech.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;

/**
 * @author AOSORIO
 *
 */

public class BackendController {

	private final static Logger slf4jLogger = LoggerFactory.getLogger(BackendController.class);

	public static Object createObject(Request pRequest, Response pResponse) {

		System.out.println(pRequest.body());
		pResponse.status(200);
		pResponse.type("application/json");
		return 0;

	}

	public static Object readObject(Request pRequest, Response pResponse) {

		NestedTestObject in1 = new NestedTestObject("My Book","Andrew, Rotschild");
		TestObject object = new TestObject( 1, in1 );
		List<TestObject> result = new ArrayList<TestObject>();
		result.add(object);
		NestedTestObject in2 = new NestedTestObject("My Second Book","Andrew, Rotschild");
		TestObject object2 = new TestObject( 2, in2 );
		result.add(object2);
		NestedTestObject in3 = new NestedTestObject("My Third and last Book","Andrew, Rotschild");
		TestObject object3 = new TestObject( 3, in3 );
		result.add(object3);
	
		slf4jLogger.info("Sending list of objects");
		
		pResponse.status(200);
		pResponse.type("application/json");
		return result;

	}

}
