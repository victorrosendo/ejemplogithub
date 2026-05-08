package com.fullstack3.ms_usuarios.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq_gen")
    @SequenceGenerator(name = "usuario_seq_gen", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene formato valido")
    @Column(name = "CORREO", nullable = false, unique = true, length = 120)
    private String correo;

    @NotBlank(message = "El password es obligatorio")
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    @Column(name = "ROL", nullable = false, length = 20)
    private String rol;

    @Column(name = "ACTIVO", nullable = false)
    private Integer activo;
}
