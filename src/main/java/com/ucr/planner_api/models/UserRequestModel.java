package com.ucr.planner_api.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



public record UserRequestModel(
    
    @NotBlank(message = "Campo requerido") 
    @Size(min = 6, max = 10, message= "El id es su carnet de usuario, en caso de no tener, poner su cédula")
    String userId,

    
    @NotBlank(message = "Campo requerido") 
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    String userName,

    
    @NotBlank(message = "Campo requerido") 
    @Email(message = "Formato de correo inválido")
    String email,
    String avatarUrl,
   
    @NotBlank(message = "Campo requerido") 
    @Size(min = 8, max = 12, message = "La contraseña debe tener entre 8 y 12 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$", 
             message = "La contraseña debe incluir mayúscula, minúscula y número")
    String password
) {}



