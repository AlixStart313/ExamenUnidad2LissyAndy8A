package utez.edu.mx.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.demo.CustomResponse;
import utez.edu.mx.demo.dtos.BookDTO;
import utez.edu.mx.demo.models.Books;
import utez.edu.mx.demo.models.BooksRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class BookServices {
    @Autowired
    private BooksRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Books>> getBooks(){
        return new CustomResponse<List<Books>>(
                this.repository.findAll(),
                    false,
                200,
                "ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Books> saveBook(BookDTO dto){
        return new CustomResponse<Books>(
                this.repository.saveAndFlush(dto.newBook()),
                false,
                200,
                "ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Books> updateBook(BookDTO dto){
        return new CustomResponse<Books>(
                this.repository.saveAndFlush(dto.updateBook()),
                false,
                200,
                "ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Books> patcheBook(BookDTO dto){
        this.repository.delete(dto.deleteBook());
        return new CustomResponse<>(
                null,
                false,
                200,
                "ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Books>> orderByDateAsc(){
        return new CustomResponse<>(
                this.orderByDateAsc().getData(),
                false,
                200,
                "ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Books>> orderByDateDesc(){
        return new CustomResponse<>(
                this.repository.findBooksByOrderByPublication_dateDesc(),
                false,
                200,
                "ok"
        );
    }
    @Transactional(readOnly = true)
    public CustomResponse<List<Books>> OrderByAutorAsc(){
        return new CustomResponse<>(
                this.repository.findBooksByOrderByAutorAsc(),
                false,
                200,
                "ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Books>> OrderByAutorDes(){
        return new CustomResponse<>(
                this.repository.findBooksByOrderByAutorDesc(),
                false,
                200,
                "ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Books>> OrderByImageAsc(){
        return new CustomResponse<>(
                this.repository.findBooksByOrderByImageAsc(),
                false,
                200,
                "ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Books>> OrderByImageDes(){
        return new CustomResponse<>(
                this.repository.findBooksByOrderByImageDesc(),
                false,
                200,
                "ok"
        );
    }

}
