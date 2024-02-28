package utez.edu.mx.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomResponse <T>{
    T data;
    boolean error;
    int statusCode;
    String message;
}