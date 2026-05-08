package com.fullstack3.ms_usuarios.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingresar un correo valido")
    private String correo;

    @NotBlank(message = "El password es obligatorio")
    private String password;
}
