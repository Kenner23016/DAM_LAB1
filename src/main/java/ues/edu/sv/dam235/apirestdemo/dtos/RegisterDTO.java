package ues.edu.sv.dam235.apirestdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String name;
    private String lastname;
    private String email;
    private String password;
}
