package utez.edu.mx.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.demo.CustomResponse;
import utez.edu.mx.demo.dtos.BookDTO;
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
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Books>> saveMovies(@RequestBody BookDTO dto){
        System.out.println(dto.getPublicationDate()+"aaaa");
        return  new ResponseEntity<>(
                this.services.saveBook(dto.newBook()),
                HttpStatus.OK
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Books>> updateMovies(@RequestBody BookDTO dto){
        return  new ResponseEntity<>(
                this.services.updateBook(dto.updateBook()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Books>> deleteMovies(@RequestBody BookDTO dto){
        return  new ResponseEntity<>(
                this.services.deleteBook(dto.deleteBook()),
                HttpStatus.OK
        );
    }

    @PostMapping ("/dates")
    public ResponseEntity<CustomResponse<List<Books>>> getByDates(@RequestParam int order){
        ResponseEntity list=  new ResponseEntity<>(
                this.services.orderByDateAsc(),
                HttpStatus.OK
        );

        if(order == 1){
            list=  new ResponseEntity<>(
                    this.services.orderByDateDesc(),
                    HttpStatus.OK
            );
        }


        return  list;
    }

    @PostMapping ("/autors")
    public ResponseEntity<CustomResponse<List<Books>>> getByAutors(@RequestParam int order){
        ResponseEntity list=  new ResponseEntity<>(
                this.services.OrderByAutorAsc(),
                HttpStatus.OK
        );

        if(order== 1){
            list=  new ResponseEntity<>(
                    this.services.orderByDateDesc(),
                    HttpStatus.OK
            );
        }
        return  list;
    }

    @PostMapping ("/images")
    public ResponseEntity<CustomResponse<List<Books>>> getByImages(@RequestParam int order){
        ResponseEntity list=  new ResponseEntity<>(
                this.services.OrderByImageAsc(),
                HttpStatus.OK
        );

        if(order== 1){
            list=  new ResponseEntity<>(
                    this.services.OrderByImageDes(),
                    HttpStatus.OK
            );
        }
        return  list;
    }


    }
