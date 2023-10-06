package digitalhands.az.controller;

import digitalhands.az.request.CategoryRequest;
import digitalhands.az.response.CategoryResponse;
import digitalhands.az.service.CategoryService;
import digitalhands.az.wrapper.CategoryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;


    @PostMapping("/add/{userId}")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest,
                                                           @PathVariable Long userId) {
        return categoryService.createCategory(categoryRequest, userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest,
                                                           @PathVariable Long userId) {
        return categoryService.updateCategory(categoryRequest, userId);
    }

    @GetMapping("/getById/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryWrapper>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{userId}/delete/{categoryId}")
    public void deleteCategory(@PathVariable Long userId,
                               @PathVariable Long categoryId) {
        categoryService.deleteById(userId, categoryId);
    }

    @DeleteMapping("/{userId}/deleteAll")
    public void deleteAllCategories(@PathVariable Long userId) {
        categoryService.deleteAllCategories(userId);
    }

}
