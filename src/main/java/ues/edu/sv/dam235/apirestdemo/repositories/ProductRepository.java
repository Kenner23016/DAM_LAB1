package ues.edu.sv.dam235.apirestdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ues.edu.sv.dam235.apirestdemo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
