package ues.edu.sv.dam235.apirestdemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ues.edu.sv.dam235.apirestdemo.dtos.LoginDTO;
import ues.edu.sv.dam235.apirestdemo.dtos.RegisterDTO;
import ues.edu.sv.dam235.apirestdemo.dtos.TokenDTO;
import ues.edu.sv.dam235.apirestdemo.services.AuthServices;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    final private AuthServices authServices;

    private AuthController(AuthServices authServices_){
        this.authServices = authServices_;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login (@RequestBody LoginDTO authRequest){
        try{
            System.out.println("DTO enviado: " + authRequest.toString());
            TokenDTO token = authServices.login(authRequest.getUser(),
                    authRequest.getPass());
            if (token == null){
                return ResponseEntity.status(401).build();
            } else {
                return ResponseEntity.ok(token);
            }
        } catch (Exception e){
            log.error("{ }", e);
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterDTO registerDTO
    ) {
        try {
            boolean registered =
                    authServices.register(registerDTO);

            if (!registered) {
                return ResponseEntity
                        .badRequest()
                        .body(
                                "No se pudo registrar usuario" +
                                        "El correo puede estar registrado"
                        );
            }

            return ResponseEntity.ok(
                    "Usuario creado correctamente"
            );
        } catch (Exception e) {
            log.error("Error al registrar usuario", e);

            return ResponseEntity
                    .internalServerError()
                    .body(
                            "Error al crear el usuario"
                    );
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestHeader("Authorization") String authorizationHeader) {

        try {

            if (authorizationHeader == null
                    || !authorizationHeader.startsWith("Bearer ")) {

                return ResponseEntity
                        .badRequest()
                        .body("Token no proporcionado");
            }

            String token =
                    authorizationHeader.substring(7);

            boolean loggedOut =
                    authServices.logout(token);

            if (!loggedOut) {

                return ResponseEntity
                        .badRequest()
                        .body("No se pudo cerrar sesión");
            }

            return ResponseEntity.ok(
                    "Sesión cerrada correctamente"
            );

        } catch (Exception e) {

            log.error("Error en logout", e);

            return ResponseEntity
                    .internalServerError()
                    .body("Error al cerrar sesión");
        }
    }
}
