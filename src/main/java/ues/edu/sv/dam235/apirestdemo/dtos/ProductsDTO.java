package ues.edu.sv.dam235.apirestdemo.dtos;

//importes
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {
    private Integer code;
    private String name;
    private Boolean status;
}
