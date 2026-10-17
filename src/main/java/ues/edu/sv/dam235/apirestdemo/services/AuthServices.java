package ues.edu.sv.dam235.apirestdemo.services;

import ues.edu.sv.dam235.apirestdemo.dtos.RegisterDTO;
import ues.edu.sv.dam235.apirestdemo.dtos.TokenDTO;

public interface AuthServices {
    public TokenDTO login(String user, String pass);

    boolean register(RegisterDTO registerDTO);

    boolean logout(String token);
}
