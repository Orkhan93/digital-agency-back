package digitalhands.az.service;

import digitalhands.az.entity.BlogPost;
import digitalhands.az.entity.Experience;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.BlogPostNotFoundException;
import digitalhands.az.exception.ExperienceNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.BlogPostMapper;
import digitalhands.az.repository.BlogPostRepository;
import digitalhands.az.repository.ExperienceRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.BlogPostRequest;
import digitalhands.az.response.BlogPostResponse;
import digitalhands.az.wrapper.BlogPostWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;
    private final BlogPostMapper blogPostMapper;
    private final ExperienceRepository experienceRepository;

    public ResponseEntity<BlogPostResponse> createBlog(BlogPostRequest blogPostRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Experience experience = experienceRepository.findById(blogPostRequest.getExperienceId()).orElseThrow(
                    () -> new ExperienceNotFoundException(ErrorMessage.EXPERIENCE_NOT_FOUND));
            BlogPost blogPost = blogPostMapper.fromRequestToModel(blogPostRequest);
            blogPost.setExperience(experience);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(blogPostMapper.fromModelToResponse(blogPostRepository.save(blogPost)));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<BlogPostResponse> updateBlog(BlogPostRequest blogPostRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            BlogPost blogPost = blogPostRepository.findById(blogPostRequest.getId()).orElseThrow(
                    () -> new BlogPostNotFoundException(ErrorMessage.BLOG_POST_NOT_FOUND));
            if (Objects.nonNull(blogPost)) {
                Experience experience = experienceRepository.findById(blogPostRequest.getExperienceId()).orElseThrow(
                        () -> new ExperienceNotFoundException(ErrorMessage.EXPERIENCE_NOT_FOUND));
                BlogPost updatedBlog = blogPostMapper.fromRequestToModel(blogPostRequest);
                blogPost.setExperience(experience);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(blogPostMapper.fromModelToResponse(blogPostRepository.save(updatedBlog)));
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<List<BlogPostWrapper>> getAllBlogs() {
        return ResponseEntity.status(HttpStatus.OK).body(blogPostRepository.getAllBlogPosts());
    }

    public ResponseEntity<BlogPostResponse> getBlogById(Long blogId) {
        BlogPost blogPost = blogPostRepository.findById(blogId).orElseThrow(
                () -> new BlogPostNotFoundException(ErrorMessage.BLOG_POST_NOT_FOUND));
        if (Objects.nonNull(blogPost)) {
            return ResponseEntity.status(HttpStatus.OK).body(blogPostMapper.fromModelToResponse(blogPost));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    public void deleteBlogPost(Long userId, Long blogPostId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            BlogPost blogPost = blogPostRepository.findById(blogPostId).orElseThrow(
                    () -> new BlogPostNotFoundException(ErrorMessage.BLOG_POST_NOT_FOUND));
            if (Objects.nonNull(blogPost)) {
                blogPostRepository.deleteById(blogPostId);
                log.info("deleteBlogPost {}", blogPost);
            }
        } else
            throw new UnauthorizedUserException(ErrorMessage.UNAUTHORIZED_USER);
    }

}