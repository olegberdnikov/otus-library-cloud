package ru.otus.homework.otuslibraryui.facadegateway.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.otuslibraryui.facadegateway.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);
}

