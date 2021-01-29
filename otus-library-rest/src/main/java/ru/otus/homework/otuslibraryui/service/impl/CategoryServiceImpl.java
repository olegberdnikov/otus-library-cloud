package ru.otus.homework.otuslibraryui.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;
import ru.otus.homework.otuslibraryui.repository.CategoryRepository;
import ru.otus.homework.otuslibraryui.service.CategoryService;
import ru.otus.homework.otuslibraryui.util.ServiceMapper;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ServiceMapper serviceMapper;

  @Override
  public List<CategoryDto> getAll() {
    return serviceMapper.domainListToCategoryDto(categoryRepository.findAll());
  }

}
