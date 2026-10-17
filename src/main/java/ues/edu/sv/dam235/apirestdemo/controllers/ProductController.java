package ues.edu.sv.dam235.apirestdemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ues.edu.sv.dam235.apirestdemo.dtos.ProductsDTO;
import ues.edu.sv.dam235.apirestdemo.entities.Product;
import ues.edu.sv.dam235.apirestdemo.services.ProductServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    final private ProductServices productServices;
    private final ListableBeanFactory listableBeanFactory;

    private ProductController(ProductServices productServices_, ListableBeanFactory listableBeanFactory){
        this.productServices = productServices_;
        this.listableBeanFactory = listableBeanFactory;
    }

    //Buscar_todo
    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getALlItems(){
        try{
            List<ProductsDTO> items = productServices.getAllProducts();
            if(items.isEmpty()){
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.ok(items);
            }
        }catch (Exception e){
            log.error("{ }", e);
        }
        return null;
    }

    //Buscar por producto
    @GetMapping("/{code}")
    public ResponseEntity<ProductsDTO> getProductByCode(
            @PathVariable Integer code
    ){
        try {
            ProductsDTO product =
                    productServices.getProductByCode(code);

            if (product == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(product);
        }catch (Exception e){
            log.error("Error al buscar el producto:", e);

            return ResponseEntity.internalServerError().build();
        }
    }

    //Crear producto
    @PostMapping
    public ResponseEntity<ProductsDTO> createProduct(
            @RequestBody ProductsDTO productsDTO
    ){
        try {
            ProductsDTO createdProduct =
                    productServices.createProduct(productsDTO);

            return ResponseEntity.ok(createdProduct);
        } catch (Exception e) {
            log.error("Error al crear el producto", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    //Actualizar producto
    @PutMapping("/{code}")
    public ResponseEntity<ProductsDTO> updateProduct(
            @PathVariable Integer code,
            @RequestBody ProductsDTO productsDTO
    ){
        try {
            ProductsDTO updatedProduct =
                    productServices.updateProduct(
                            code,
                            productsDTO
                    );

            if (updatedProduct == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            log.error("Error al actualizar producto", e);

            return ResponseEntity.internalServerError().build();
        }
    }

    //Borrar producto
    @DeleteMapping("/{code}")
    public ResponseEntity<ProductsDTO> deleteProduct(
            @PathVariable Integer code,
            @RequestBody ProductsDTO productsDTO
    ){
        try {
            boolean deleted =
                    productServices.deleteProduct(code);

            if (!deleted){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al eliminar producto", e);

            return ResponseEntity.internalServerError().build();
        }
    }
}
