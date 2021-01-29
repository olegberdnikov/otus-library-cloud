package ru.otus.homework.otuslibraryui.util;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.otus.homework.otuslibraryui.domain.Author;
import ru.otus.homework.otuslibraryui.domain.Book;
import ru.otus.homework.otuslibraryui.domain.Category;
import ru.otus.homework.otuslibraryui.domain.Comment;
import ru.otus.homework.otuslibraryui.dto.AuthorDto;
import ru.otus.homework.otuslibraryui.dto.BookDto;
import ru.otus.homework.otuslibraryui.dto.CategoryDto;
import ru.otus.homework.otuslibraryui.dto.CommentDto;

@Mapper
public interface ServiceMapper {

  Author dtoToAuthorDomain(AuthorDto authorDto);

  AuthorDto domainToAuthorDto(Author author);

  Book dtoToBookDomain(BookDto bookDto);

  BookDto domainToBookDto(Book book);

  Comment dtoToCommentDomain(CommentDto commentDto);

  CommentDto domainToCommentDto(Comment comment);

  Category dtoToCategoryDomain(CategoryDto categoryDto);

  CommentDto domainToCategoryDto(Category category);

  List<CategoryDto> domainListToCategoryDto(List<Category> categoryList);

  List<AuthorDto> domainListToAuthorDto(List<Author> authorList);

  @Mapping(target = "comments", ignore = true)
  void updateBookFromDto(BookDto dto, @MappingTarget Book entity);
}
