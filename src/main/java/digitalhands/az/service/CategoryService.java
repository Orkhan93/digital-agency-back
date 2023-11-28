package digitalhands.az.service;

import digitalhands.az.entity.Category;
import digitalhands.az.entity.Collection;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.CategoryNotFoundException;
import digitalhands.az.exception.CollectionNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.CategoryMapper;
import digitalhands.az.repository.CategoryRepository;
import digitalhands.az.repository.CollectionRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.CategoryRequest;
import digitalhands.az.response.CategoryResponse;
import digitalhands.az.response.CategoryResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(CategoryRequest categoryRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Collection collection = collectionRepository.findById(categoryRequest.getCollectionId()).orElseThrow(
                    () -> new CollectionNotFoundException(ErrorMessage.COLLECTION_NOT_FOUND));
            Category category = categoryMapper.fromRequestToModel(categoryRequest);
            category.setCollection(collection);
            return categoryMapper.fromModelToResponse(categoryRepository.save(category));
        } else
            throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Category findCategory = categoryRepository
                    .findById(categoryRequest.getId()).orElseThrow(
                            () -> new CategoryNotFoundException(HttpStatus.NOT_FOUND.name(),
                                    ErrorMessage.CATEGORY_NOT_FOUND));
            if (Objects.isNull(findCategory)) {
                throw new CategoryNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.CATEGORY_NOT_FOUND);
            } else {
                Collection collection = collectionRepository.findById(categoryRequest.getCollectionId()).orElseThrow(
                        () -> new CollectionNotFoundException(ErrorMessage.COLLECTION_NOT_FOUND));
                Category category = categoryMapper.fromRequestToModel(categoryRequest);
                category.setCollection(collection);
                return categoryMapper.fromModelToResponse(categoryRepository.save(category));
            }
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public CategoryResponseList getAllCategories() {
        List<Category> all = categoryRepository.findAll();
        CategoryResponseList list = new CategoryResponseList();
        List<CategoryResponse> categoryResponses = categoryMapper.fromModelListToResponseList(all);
        list.setCategoryResponses(categoryResponses);
        return list;
    }

    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.CATEGORY_NOT_FOUND));
        return categoryMapper.fromModelToResponse(category);
    }

    public void deleteById(Long userId, Long categoryId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Category category = categoryRepository
                    .findById(categoryId).orElseThrow(
                            () -> new CategoryNotFoundException(HttpStatus.NOT_FOUND.name(),
                                    ErrorMessage.CATEGORY_NOT_FOUND));
            categoryRepository.deleteById(categoryId);
            log.info("deleteById {}", category);
        }
    }

    public void deleteAllCategories(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            categoryRepository.deleteAll();
            log.info("deleteAllCategories successfully");
        }
    }

}