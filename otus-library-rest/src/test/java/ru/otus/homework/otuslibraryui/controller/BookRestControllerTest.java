package ru.otus.homework.otuslibraryui.controller;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.dto.BookDto;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;
import ru.otus.homework.otuslibraryui.service.AuthorService;
import ru.otus.homework.otuslibraryui.service.BookService;
import ru.otus.homework.otuslibraryui.service.CategoryService;

@DisplayName("Контроллер rest книги")
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookRestController.class)
class BookRestControllerTest {


  private static final Long BOOK_ID = 1L;
  public static final String EDIT_BOOK_NAME = "Звезда Пандоры1";

  @MockBean
  private BookService bookService;
  @MockBean
  private CategoryService categoryService;
  @MockBean
  private AuthorService authorService;

  @Autowired
  private MockMvc mockMvc;
  private AuthorDto author;
  @BeforeEach
  public void setUp() throws Exception {
    author=AuthorDto.builder().authorId(BOOK_ID).name("authorName")
        .name2("authorName2")
        .surname("authorSurname")
        .build();
    final BookDto bookDto = BookDto.builder().bookId(1L)
        .authors(Set.of(author))
        .comments(new ArrayList<>())
        .category(CategoryDto.builder().categoryId(1L).name("category").build()).build();

    given(bookService.findById(isA(Long.class))).willReturn(bookDto);
    given(bookService.update(isA(BookDto.class))).willReturn(bookDto);
    given(bookService.save(isA(BookDto.class))).willReturn(bookDto);
    doNothing().when(bookService).deleteById(isA(Long.class));
    List<AuthorDto> authorDtos=new ArrayList<>();
    authorDtos.add(AuthorDto.builder().authorId(1L).name("1212").name2("122").build());
    authorDtos.add(AuthorDto.builder().authorId(2L).name("1212").name2("122").build());
    authorDtos.add(AuthorDto.builder().authorId(3L).name("1212").name2("122").build());
    authorDtos.add(AuthorDto.builder().authorId(4L).name("1212").name2("122").build());

    given(authorService.getAllByIds(isA(List.class))).will(
        (InvocationOnMock invocation) -> {
          final List<Long> authors = (List<Long>) invocation.getArguments()[0];
          final List<AuthorDto> authorDtos1 = authorDtos.stream()
              .filter(p -> authors.stream().anyMatch(s -> s.equals(p.getAuthorId())))
              .collect(Collectors.toList());
          return authorDtos1;
        });

    given(authorService.getAll()).willReturn(authorDtos);

    List<CategoryDto> categoryDtos=new ArrayList<>();
    categoryDtos.add(CategoryDto.builder().name("category1").categoryId(1L).build());
    categoryDtos.add(CategoryDto.builder().name("category2").categoryId(2L).build());
    categoryDtos.add(CategoryDto.builder().name("category3").categoryId(3L).build());
    given(categoryService.getAll()).willReturn(categoryDtos);


    Pageable pageable = PageRequest.of(1, 1);
    Page<BookDto> page = new PageImpl<>(List.of(bookDto), pageable, 1);
    given(bookService.getPaginatedListBooks(isA(Pageable.class))).willReturn(page);
  }

  @Test
  void listBooks() throws Exception {
    mockMvc
        .perform(get("/books/"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].BookId").value(BOOK_ID));
  }

  @Test
  void getBook() throws Exception {
    mockMvc
        .perform(get("/book/"+BOOK_ID))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.BookId").value(BOOK_ID));
  }

  @Test
  void deleteBook() throws Exception {
    mockMvc
        .perform(delete("/book/"+BOOK_ID))
        .andExpect(status().isOk());
  }

  @Test
  void addBook() throws Exception {
    final BookDto bookDto = BookDto.builder()
        .name("name2")
        .authors(Set.of(author))
        .comments(new ArrayList<>())
        .category(CategoryDto.builder().categoryId(1L).name("category").build()).build();

    mockMvc
        .perform(
            post("/book/")
                .content(new ObjectMapper().writeValueAsBytes(bookDto))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$").value(BOOK_ID));
  }

  @Test
  void editBook() throws Exception {
    final BookDto bookDto = BookDto.builder()
        .name("name2")
        .authors(Set.of(author))
        .comments(new ArrayList<>())
        .category(CategoryDto.builder().categoryId(1L).name("category").build()).build();

    mockMvc
        .perform(
            put("/book/")
                .content(new ObjectMapper().writeValueAsBytes(bookDto))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").value(BOOK_ID));
  }

}