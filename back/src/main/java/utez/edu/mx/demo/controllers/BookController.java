package utez.edu.mx.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utez.edu.mx.demo.CustomResponse;
import utez.edu.mx.demo.models.Books;
import utez.edu.mx.demo.services.BookServices;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = {"*"})
public class BookController {

    @Autowired
    private BookServices services;

    @GetMapping ("/")
    public ResponseEntity<CustomResponse<List<Books>>> getMovies(){
        return  new ResponseEntity<>(
                this.services.getBooks(),
                HttpStatus.OK
        );
    }
}
