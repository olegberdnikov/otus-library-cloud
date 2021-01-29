package ru.otus.homework.otuslibraryui.facadegateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.homework.otuslibraryui.facadegateway.domain.Authority;
import ru.otus.homework.otuslibraryui.facadegateway.domain.User;
import ru.otus.homework.otuslibraryui.facadegateway.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    final User user =
        userRepository
            .findByName(username)
            .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    UserBuilder builder = null;
    builder = org.springframework.security.core.userdetails.User.withUsername(username);
    builder.password(user.getPassword());
    final String[] roles =
        user.getAuthorities().stream().map(Authority::getAuthority).toArray(String[]::new);
    builder.roles(roles);
    return builder.build();
  }

}
