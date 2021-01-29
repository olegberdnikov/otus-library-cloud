package ru.otus.homework.otuslibraryui.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.dto.BookDto;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;
import ru.otus.homework.otuslibraryui.feign.RestService;

@DisplayName("Контроллер view книги")
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookPageController.class)
class BookPageControllerTest {

  private static final Long BOOK_ID = 1L;
  public static final String EDIT_BOOK_NAME = "Звезда Пандоры1";

  @MockBean
  private RestService restService;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    AuthorDto author=AuthorDto.builder().authorId(BOOK_ID).name("authorName")
        .name2("authorName2")
        .surname("authorSurname")
        .build();
    final BookDto bookDto = BookDto.builder().bookId(1L)
        .authors(Set.of(author))
        .comments(new ArrayList<>())
        .category(CategoryDto.builder().categoryId(1L).name("category").build()).build();

    List<AuthorDto> authorDtos=new ArrayList<>();
    authorDtos.add(AuthorDto.builder().authorId(1L).name("1212").name2("122").build());
    authorDtos.add(AuthorDto.builder().authorId(2L).name("1212").name2("122").build());
    authorDtos.add(AuthorDto.builder().authorId(3L).name("1212").name2("122").build());
    authorDtos.add(AuthorDto.builder().authorId(4L).name("1212").name2("122").build());


    given(restService.getAuthors()).willReturn(authorDtos);

    List<CategoryDto> categoryDtos=new ArrayList<>();
    categoryDtos.add(CategoryDto.builder().name("category1").categoryId(1L).build());
    categoryDtos.add(CategoryDto.builder().name("category2").categoryId(2L).build());
    categoryDtos.add(CategoryDto.builder().name("category3").categoryId(3L).build());
    given(restService.getCategories()).willReturn(categoryDtos);

  }


  @Test
  void listBook() throws Exception {
    mockMvc
        .perform(get("/book/booklist"))
        .andExpect(status().isOk());
  }

  @Test
  void viewBook() throws Exception {
    mockMvc
        .perform(get("/book/view/"+BOOK_ID))
        .andExpect(status().isOk());
  }

  @Test
  void deleteBook() throws Exception {
    mockMvc
        .perform(get("/book/delete/"+BOOK_ID))
        .andExpect(status().isOk());
  }

  @Test
  void addBook() throws Exception {
    mockMvc
        .perform(get("/book/addnew"))
        .andExpect(status().isOk());
  }

  @Test
  void editBook() throws Exception {
    mockMvc
        .perform(get("/book/edit/"+BOOK_ID))
        .andExpect(status().isOk());
  }

}