package utez.edu.mx.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import utez.edu.mx.demo.models.Books;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    private String name;

    private String autor;

    private String image;

    private Date publicationDate;

    public Books newBook(){
        System.out.println(getPublicationDate()+"DTO");
        return new Books(
                 null,
                getName(),
                getAutor(),
                getImage(),
                getPublicationDate()
        );
    }

    public Books deleteBook(){
        Date today = new Date();
        return new Books(
                getId(),
                null,
               null,
                null,
                null
        );
    }

    public Books updateBook(){
        Date today = new Date();
        return new Books(
                getId(),
                getName(),
                getAutor(),
                getImage(),
                getPublicationDate()
        );
    }
}
