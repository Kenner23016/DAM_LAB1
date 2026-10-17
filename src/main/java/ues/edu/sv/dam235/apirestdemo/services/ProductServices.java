package ues.edu.sv.dam235.apirestdemo.services;

import ues.edu.sv.dam235.apirestdemo.dtos.ProductsDTO;
import ues.edu.sv.dam235.apirestdemo.entities.Product;

import java.util.List;

public interface ProductServices {
    public List<ProductsDTO> getAllProducts();

    ProductsDTO getProductByCode(Integer code);
    ProductsDTO createProduct(ProductsDTO product);
    ProductsDTO updateProduct(Integer code, ProductsDTO product);
    boolean deleteProduct(Integer code);
}
