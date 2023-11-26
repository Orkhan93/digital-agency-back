package digitalhands.az.service;

import digitalhands.az.entity.BlogPost;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.BlogPostNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.BlogPostMapper;
import digitalhands.az.repository.BlogPostRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.BlogPostRequest;
import digitalhands.az.response.BlogPostResponse;
import digitalhands.az.response.BlogPostResponseList;
import digitalhands.az.wrapper.BlogPostWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;
    private final BlogPostMapper blogPostMapper;

    public BlogPostResponse createBlog(BlogPostRequest blogPostRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            BlogPost blogPost = blogPostMapper.fromRequestToModel(blogPostRequest);
            blogPost.setCreationDate(LocalDateTime.now());
            return blogPostMapper.fromModelToResponse(blogPostRepository.save(blogPost));
        }
        throw new UnauthorizedUserException(ErrorMessage.UNAUTHORIZED_USER);
        // TODO UnauthorizedException (String code) - modified
    }

    public BlogPostResponse updateBlog(BlogPostRequest blogPostRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            BlogPost blogPost = blogPostRepository.findById(blogPostRequest.getId()).orElseThrow(
                    () -> new BlogPostNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BLOG_POST_NOT_FOUND));
            if (Objects.isNull(blogPost)) {
                throw new BlogPostNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BLOG_POST_NOT_FOUND);
            } else {
                BlogPost updatedBlog = blogPostMapper.fromRequestToModel(blogPostRequest);
                updatedBlog.setCreationDate(LocalDateTime.now());
                return blogPostMapper.fromModelToResponse(blogPostRepository.save(updatedBlog));
            }
        } else
            throw new UnauthorizedUserException(ErrorMessage.UNAUTHORIZED_USER);
    }

    public BlogPostResponseList getAllBlogs() {
        List<BlogPost> all = blogPostRepository.findAll();
        BlogPostResponseList list = new BlogPostResponseList();
        List<BlogPostResponse> blogPostResponses = blogPostMapper.fromModelListToResponseList(all);
        list.setBlogPostResponses(blogPostResponses);
        return list;
    }

    public BlogPostResponse getBlogById(Long blogId) {
        BlogPost blogPost = blogPostRepository.findById(blogId).orElseThrow(
                () -> new BlogPostNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BLOG_POST_NOT_FOUND));
        return blogPostMapper.fromModelToResponse(blogPost);
    }

    public void deleteBlogPost(Long userId, Long blogPostId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            BlogPost blogPost = blogPostRepository.findById(blogPostId).orElseThrow(
                    () -> new BlogPostNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.BLOG_POST_NOT_FOUND));
            if (Objects.nonNull(blogPost)) {
                blogPostRepository.deleteById(blogPostId);
                log.info("deleteBlogPost {}", blogPost);
            }
        } else
            throw new UnauthorizedUserException(ErrorMessage.UNAUTHORIZED_USER);
    }

}