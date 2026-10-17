package ues.edu.sv.dam235.apirestdemo.entities;

//importamos
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "[User]")
public class User {

    //definimos ip (clave primaria-al ser db es indispensable
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Boolean active;

}
