package co.phystech.models;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IModel {

	UUID createPost(String title, String content, List<String> categories);

	UUID createComment(UUID post, String author, String content);

	List<Post> getAllPosts();

	List<Comment> getAllCommentsOn(UUID post);

	boolean existPost(UUID post);

	Optional<Post> getPost(UUID uuid);

	void updatePost(Post post);

	void deletePost(UUID uuid);

}
