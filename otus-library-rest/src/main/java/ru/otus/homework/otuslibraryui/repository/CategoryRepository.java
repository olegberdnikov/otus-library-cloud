package ru.otus.homework.otuslibraryui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.otuslibraryui.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
