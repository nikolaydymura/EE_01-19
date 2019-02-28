package edu.ee.repositories;

import edu.ee.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class JdbcBookRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Transactional(readOnly = true)
  public List<BookDTO> getBook(Integer id) {
    return jdbcTemplate.queryForList("select * from books where id=", BookDTO.class);
  }
}
