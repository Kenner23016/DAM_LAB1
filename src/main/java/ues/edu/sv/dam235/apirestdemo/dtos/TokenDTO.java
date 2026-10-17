package ues.edu.sv.dam235.apirestdemo.dtos;

//Importes
import lombok.Data;

@Data
public class TokenDTO {
    private String token;
    private String expireIn;
    private String msj;
}
