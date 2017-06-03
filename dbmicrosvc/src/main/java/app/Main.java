/**
 * 
 */
package app;

import static spark.Spark.*;

/**
 * @author AOSORIO
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		get("/hello", (req, res) -> "Hello World");

		/*
		 * get("") { req, res ->
		 * jacksonObjectMapper().writeValueAsString(userDao.users) }
		 * 
		 * get("/:id") { req, res -> userDao.findById(req.params("id").toInt())
		 * }
		 * 
		 * get("/email/:email") { req, res ->
		 * userDao.findByEmail(req.params("email")) }
		 * 
		 * post("/create") { req, res -> userDao.save(name = req.qp("name"),
		 * email = req.qp("email")) res.status(201) "ok" }
		 * 
		 * patch("/update/:id") { req, res -> userDao.update( id =
		 * req.params("id").toInt(), name = req.qp("name"), email =
		 * req.qp("email") ) "ok" }
		 * 
		 * delete("/delete/:id") { req, res ->
		 * userDao.delete(req.params("id").toInt()) "ok" }
		 * 
		 */

	}

}
