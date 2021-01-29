package ru.otus.homework.otuslibraryui.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.otuslibraryui.config.MapperConfig;
import ru.otus.homework.otuslibraryui.domain.Author;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.repository.AuthorRepository;
import ru.otus.homework.otuslibraryui.service.AuthorService;
import ru.otus.homework.otuslibraryui.util.ServiceMapper;

@DisplayName("Сервисный слой для работы с авторами")
@ExtendWith(SpringExtension.class)
@Import({AuthorServiceImpl.class})
@ContextConfiguration(classes = MapperConfig.class)
class AuthorServiceImplTest {

  public static final long AUTHOR_ID = 3L;
  @MockBean private AuthorRepository authorRepository;
  @Autowired private ServiceMapper serviceMapper;

  @Autowired AuthorService authorService;
  final ArrayList<Author> authorArrayList = new ArrayList<>();

  @BeforeEach
  public void setUp() throws Exception {
    final Author author =
        Author.builder()
            .authorId(AUTHOR_ID)
            .name("Пурышева")
            .name2("Наталья")
            .surname("Сергеевная")
            .build();
    authorArrayList.add(author);
    given(authorRepository.findAll()).willReturn(authorArrayList);
    given(authorRepository.findAllById(isA(List.class))).willReturn(authorArrayList);
  }

  @Test
  void getAll() {
    List<AuthorDto> all = authorService.getAll();
    assertThat(all)
        .isNotEmpty()
        .containsExactly(
            serviceMapper.domainListToAuthorDto(authorArrayList).toArray(AuthorDto[]::new));
  }

  @Test
  void getAllByIds() {
    final List<AuthorDto> allByIds = authorService.getAllByIds(Arrays.asList(AUTHOR_ID));
    assertThat(allByIds)
        .isNotEmpty()
        .containsExactly(
            serviceMapper.domainListToAuthorDto(authorArrayList).toArray(AuthorDto[]::new));
  }

}
