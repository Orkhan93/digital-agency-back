package digitalhands.az.controller;

import digitalhands.az.request.CategoryRequest;
import digitalhands.az.response.CategoryResponse;
import digitalhands.az.response.CategoryResponseList;
import digitalhands.az.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest,
                                                           @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest,
                                                           @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryRequest, userId));
    }

    @GetMapping("/getById/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<CategoryResponseList> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
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