package utez.edu.mx.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books,Long> {

    List<Books> findBooksByOrderByAutorDesc();
    List<Books> findBooksByOrderByAutorAsc();

    List<Books> findBooksByOrderByPublicationDateDesc();

    List<Books> findBooksByOrderByPublicationDateAsc();

    List<Books> findBooksByOrderByImageAsc();
    List<Books> findBooksByOrderByImageDesc();
}
