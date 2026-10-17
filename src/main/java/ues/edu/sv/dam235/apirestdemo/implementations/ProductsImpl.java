package ues.edu.sv.dam235.apirestdemo.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ues.edu.sv.dam235.apirestdemo.dtos.ProductsDTO;
import ues.edu.sv.dam235.apirestdemo.entities.Product;
import ues.edu.sv.dam235.apirestdemo.repositories.ProductRepository;
import ues.edu.sv.dam235.apirestdemo.services.ProductServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductsImpl implements ProductServices {
    private final ProductRepository productRepository;

    private ProductsImpl (ProductRepository productRepository_){
        this.productRepository = productRepository_;
    }

    @Override
    public List<ProductsDTO> getAllProducts(){
        List<ProductsDTO> result = new ArrayList<>();
        List<Product> items = this.productRepository.findAll();
        for (Product item: items){
            result.add(new ProductsDTO(
                    item.getCode(),
                    item.getName(),
                    item.getStatus()));
        }
        return result;
    }

    @Override
    public ProductsDTO getProductByCode(Integer code) {

        Optional<Product> productOptional =
                productRepository.findById(code);

        if (productOptional.isPresent()) {

            Product product = productOptional.get();

            return new ProductsDTO(
                    product.getCode(),
                    product.getName(),
                    product.getStatus()
            );
        }

        return null;
    }

    @Override
    public ProductsDTO createProduct(ProductsDTO productDTO) {

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setStatus(productDTO.getStatus());

        Product savedProduct =
                productRepository.save(product);

        return new ProductsDTO(
                savedProduct.getCode(),
                savedProduct.getName(),
                savedProduct.getStatus()
        );
    }

    @Override
    public ProductsDTO updateProduct(
            Integer code,
            ProductsDTO productsDTO
    ){
        Optional<Product> productOptional =
                productRepository.findById(code);

        if(productOptional.isEmpty()){
            return null;
        }

        Product product = productOptional.get();

        product.setName(productsDTO.getName());
        product.setStatus(productsDTO.getStatus());

        Product updatedProduct =
                productRepository.save(product);

        return new ProductsDTO(
                updatedProduct.getCode(),
                updatedProduct.getName(),
                updatedProduct.getStatus()
        );
    }

    @Override
    public boolean deleteProduct(Integer code) {

        Optional<Product> productOptional =
                productRepository.findById(code);

        if(productOptional.isEmpty()){
            return false;
        }

        productRepository.deleteById(code);

        return true;
    }


}
