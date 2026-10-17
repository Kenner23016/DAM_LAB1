package ues.edu.sv.dam235.apirestdemo.entities;

//Importamos
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product {

    //generamos id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "code")
    private Integer code;

    @Column(name = "Name")
    private String name;

    @Column(name = "Status")
    private Boolean status;
}
