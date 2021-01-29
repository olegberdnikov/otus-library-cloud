package ru.otus.homework.otuslibraryui.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.repository.AuthorRepository;
import ru.otus.homework.otuslibraryui.service.AuthorService;
import ru.otus.homework.otuslibraryui.util.ServiceMapper;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final ServiceMapper serviceMapper;

  @Override
  public List<AuthorDto> getAll() {
    return serviceMapper.domainListToAuthorDto(authorRepository.findAll());
  }

  @Override
  public List<AuthorDto> getAllByIds(List<Long> authorIds) {
    return serviceMapper.domainListToAuthorDto(authorRepository.findAllById(authorIds));
  }

}
