package ues.edu.sv.dam235.apirestdemo.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ues.edu.sv.dam235.apirestdemo.configs.CustomerDetailServices;
import ues.edu.sv.dam235.apirestdemo.dtos.RegisterDTO;
import ues.edu.sv.dam235.apirestdemo.dtos.TokenDTO;
import ues.edu.sv.dam235.apirestdemo.entities.User;
import ues.edu.sv.dam235.apirestdemo.repositories.UserRepository;
import ues.edu.sv.dam235.apirestdemo.services.AuthServices;
import ues.edu.sv.dam235.apirestdemo.services.TokenBlacklistService;
import ues.edu.sv.dam235.apirestdemo.utilities.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;

@Slf4j
@Service
public class AuthServicesImpl implements AuthServices {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerDetailServices customerDetailServices;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Override
    public TokenDTO login(String user, String pass){
        TokenDTO token = new TokenDTO() ;
        try{
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(user, pass)
            );
            if (authentication.isAuthenticated()) {
                UserDetails usuarioDetail = (UserDetails)
                        authentication.getPrincipal();
                if (customerDetailServices.getUserDetail().getActive()) {
                    token = jwtUtil.generateToken(user, usuarioDetail);
                    return token;
                }
            }
        } catch (BadCredentialsException bad) {
            token.setMsj("Credenciales incorrectas!");
            return null;
        } catch (Exception e) {
            log.error("{}", e);
            token.setMsj("Error innesperado");
            return null;
        }
        return null;
    }

    //Creacion de nuevop usuario
    @Override
    public boolean register (RegisterDTO registerDTO) {
        try {
            //Ver si el correo ya existe
            User existingUser =
                    userRepository.findByEmail(registerDTO.getEmail());

            if (existingUser != null) { //validar que no exista
                return false;
            }

            User newUser = new User();

            newUser.setName(registerDTO.getName());
            newUser.setLastName(registerDTO.getLastname());
            newUser.setEmail(registerDTO.getEmail());
            //Ciframos contraseña
            newUser.setPassword(
                    passwordEncoder.encode(registerDTO.getPassword())
            );
            newUser.setActive(true);

            userRepository.save(newUser);

            return true;
        } catch (Exception e) {
            log.error("Error al registrar usuario", e);

            return false;
        }
    }

    @Override
    public boolean logout(String token) {

        try {

            if (token == null || token.isBlank()) {
                return false;
            }

            tokenBlacklistService.blacklistToken(token);

            return true;

        } catch (Exception e) {

            log.error("Error al cerrar sesión", e);

            return false;
        }
    }
}
