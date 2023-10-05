package digitalhands.az.controller;

import digitalhands.az.request.BlogPostRequest;
import digitalhands.az.response.BlogPostResponse;
import digitalhands.az.service.BlogPostService;
import digitalhands.az.wrapper.BlogPostWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog-post")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<BlogPostResponse> createBlog(@RequestBody BlogPostRequest blogPostRequest,
                                                       @PathVariable Long userId) {
        return blogPostService.createBlog(blogPostRequest, userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<BlogPostResponse> updateBlog(@RequestBody BlogPostRequest blogPostRequest,
                                                       @PathVariable Long userId) {
        return blogPostService.updateBlog(blogPostRequest, userId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BlogPostWrapper>> getAllBlogs() {
        return blogPostService.getAllBlogs();
    }

    @GetMapping("/get/{blogPostId}")
    public ResponseEntity<BlogPostResponse> getBlogById(@PathVariable Long blogPostId) {
        return blogPostService.getBlogById(blogPostId);
    }

    @DeleteMapping("/{userId}/delete/{blogPostId}")
    public void deleteBlogPost(@PathVariable(name = "userId") Long userId,
                               @PathVariable(name = "blogPostId") Long blogPostId) {
        blogPostService.deleteBlogPost(userId, blogPostId);
    }

}