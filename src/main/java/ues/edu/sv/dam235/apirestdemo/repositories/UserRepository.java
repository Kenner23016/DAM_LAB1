package ues.edu.sv.dam235.apirestdemo.repositories;

//Import archivos
import ues.edu.sv.dam235.apirestdemo.entities.User;

//Importes jpa
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = :email ")
    User findByEmail(@Param("email") String email);

}
