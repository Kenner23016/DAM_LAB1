package ues.edu.sv.dam235.apirestdemo.dtos;

//Importes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String User;
    private String pass;
}
