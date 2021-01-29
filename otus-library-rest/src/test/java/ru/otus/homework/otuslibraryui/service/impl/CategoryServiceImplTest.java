package ru.otus.homework.otuslibraryui.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
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
import ru.otus.homework.otuslibraryui.domain.Category;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;
import ru.otus.homework.otuslibraryui.repository.CategoryRepository;
import ru.otus.homework.otuslibraryui.service.CategoryService;
import ru.otus.homework.otuslibraryui.util.ServiceMapper;

@DisplayName("Сервисный слой для работы с категориями")
@ExtendWith(SpringExtension.class)
@Import(CategoryServiceImpl.class)
@ContextConfiguration(classes = MapperConfig.class)
class CategoryServiceImplTest {

  public static final long CATEGORY_ID = 1L;
  @MockBean
  private CategoryRepository categoryRepository;
  @Autowired
  private ServiceMapper serviceMapper;

  @Autowired
  CategoryService categoryService;

  final ArrayList<Category> categoryList = new ArrayList<>();

  @BeforeEach
  public void setUp() throws Exception {
    final Category category =Category.builder().categoryId(CATEGORY_ID)
        .name("CategoryName").build();
    categoryList.add(category);
    given(categoryRepository.findAll()).willReturn(categoryList);
  }

  @Test
  void getAll() {
    List<CategoryDto> all = categoryService.getAll();
    assertThat(all)
        .isNotEmpty().contains(serviceMapper.domainListToCategoryDto(categoryList)
        .toArray(CategoryDto[]::new));
  }

}