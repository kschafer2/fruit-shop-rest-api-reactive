package guru.springframework.sfgfruitshoprestreactive.controllers;


import guru.springframework.sfgfruitshoprestreactive.domain.Category;
import guru.springframework.sfgfruitshoprestreactive.repositories.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static guru.springframework.sfgfruitshoprestreactive.controllers.CategoryController.CATEGORIES_BASE_URL;

@RestController
@RequestMapping(CATEGORIES_BASE_URL)
public class CategoryController {

    public static final String CATEGORIES_BASE_URL = "/api/v1/categories";
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Category> getCategoryById(@PathVariable String id) {
        return categoryRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createNewCategory(@RequestBody Publisher<Category> categoryPublisher) {
        return categoryRepository.saveAll(categoryPublisher).then();
    }

    @PutMapping("/{id}")
    Mono<Category> overwriteCategory(@PathVariable String id, @RequestBody Category category) {
        category.setId(id);

        return categoryRepository.save(category);
    }
}
