/**
 * 
 */
package sql2oexp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import static org.junit.Assert.*;

import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import co.phystech.models.IModel;
import co.phystech.models.NewProjectPayload;
import co.phystech.models.Post;
import co.phystech.models.Sql2oModel;

/**
 * @author AOSORIO
 *
 */
public class createDataTests {

	@Test
	public void createPost() {

		String address = new String("jdbc:postgresql://localhost:5432/blog");
		String dbUsername = new String("aosorio");
		String dbPassword = new String("sparkforthewin");

		Sql2o sql2o = new Sql2o(address, dbUsername, dbPassword, new PostgresQuirks() {
			{
				// make sure we use default UUID converter.
				converters.put(UUID.class, new UUIDConverter());
			}
		});

		IModel model = new Sql2oModel(sql2o);

		NewProjectPayload creation = new NewProjectPayload();

		String title = "My new Post";
		String content = "This is the content";
		List<String> categories = new ArrayList<String>();
		categories.add("Category One");
		categories.add("Category Two");

		creation.setTitle(title);
		creation.setContent(content);
		creation.setCategories(categories);

		UUID id = model.createPost(creation.getTitle(), creation.getContent(), creation.getCategories());

		List<Post> posts = new ArrayList<Post>();
		posts = model.getAllPosts();
		
		Post lastPost = posts.get(posts.size() - 1);
		
		//System.out.println(id);
		//System.out.println(posts.size());
		//System.out.println(lastPost.getPost_uuid());

		assertEquals(id, lastPost.getPost_uuid());

	}

}
