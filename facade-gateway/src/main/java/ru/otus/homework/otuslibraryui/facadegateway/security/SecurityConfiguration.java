package ru.otus.homework.otuslibraryui.facadegateway.security;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final DataSource dataSource;

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers(
            "/ui/scripts/**", "/ui/css/**", "/ui/images/**", "/ui/webjars/**", "/ui/h2-console/", "/ui/h2-console/**");
  }
  
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .headers()
        .frameOptions()
        .sameOrigin()
        .and()
        .authorizeRequests()
        .antMatchers("/login", "/login**").permitAll()
        .antMatchers(HttpMethod.GET, "/ui/book/view/**","/ui/", "/ui/book/booklist", "/ui/book/**","/rest/book/**","/rest/books/**","/rest/authors/**").hasRole("USER")
        .antMatchers(HttpMethod.GET, "/ui/book/edit/**", "/ui/book/addnew").hasRole("LIBRARIAN")
        .antMatchers(HttpMethod.POST, "/rest/book/").hasRole("LIBRARIAN")
        .antMatchers(HttpMethod.PUT, "/rest/book/").hasRole("LIBRARIAN")
        .antMatchers(HttpMethod.GET, "/ui/book/delete/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/rest/book/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/ui/error/error", "/ui/error/security-error").authenticated()
        .antMatchers("/**").denyAll()
        .and()
        // Включает Form-based аутентификацию
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .rememberMe()
        .rememberMeParameter("remember-me")
        .tokenRepository(tokenRepository())
        .userDetailsService(userDetailsService)
        .rememberMeCookieName("remember-me-cookie-name")
        .tokenValiditySeconds(60 * 60 * 24)
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
        .permitAll();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  };

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PersistentTokenRepository tokenRepository() {
    JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
    jdbcTokenRepositoryImpl.setDataSource(dataSource);
    jdbcTokenRepositoryImpl.setCreateTableOnStartup(false);
    return jdbcTokenRepositoryImpl;
  }

}
