package guru.springframework.sfgfruitshoprestreactive.controllers;

import guru.springframework.sfgfruitshoprestreactive.domain.Category;
import guru.springframework.sfgfruitshoprestreactive.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static guru.springframework.sfgfruitshoprestreactive.controllers.CategoryController.CATEGORIES_BASE_URL;
import static org.mockito.BDDMockito.given;

public class CategoryControllerTest {

    public static final String ID = "id";
    WebTestClient webTestClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;

    @Before
    public void setUp() throws Exception {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void getAllCategoriesTest() {
        given(categoryRepository.findAll())
                .willReturn(Flux.just(
                Category.builder().name("name1").build(),
                Category.builder().name("name2").build()));

        webTestClient.get()
                .uri(CATEGORIES_BASE_URL)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    public void getCategoryByIdTest() {
        given(categoryRepository.findById(ID))
                .willReturn(Mono.just(Category.builder().name("name").build()));

        webTestClient.get()
                .uri(CATEGORIES_BASE_URL + "/" + ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Category.class);
    }
}