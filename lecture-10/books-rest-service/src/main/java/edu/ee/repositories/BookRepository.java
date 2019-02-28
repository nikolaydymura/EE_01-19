package edu.ee.repositories;

import edu.ee.models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
  BookEntity findBookEntityByTitle(String title);

  //@Query(nativeQuery = true, value = "select * from BOOKS where title=:title")
  //BookEntity varyComplexSelect(String title);

  @Query(value = "select b from Book b where title=:title")
  BookEntity varyComplexSelectNotNative(String title);

  ///worker_id
  ///manager_id
  ///select * from workers w, workers m where m.worker_id= w.manager_id;

}
