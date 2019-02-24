package edu.ee.repositories;

import edu.ee.models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
  BookEntity findBookEntityByTitle(String title);
}
