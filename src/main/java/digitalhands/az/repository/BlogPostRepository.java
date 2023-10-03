package digitalhands.az.repository;

import digitalhands.az.entity.BlogPost;
import digitalhands.az.wrapper.BlogPostWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPostWrapper> getAllBlogPosts();

}