package com.fullstack3.ms_usuarios.service;


import org.springframework.stereotype.Service;

import com.fullstack3.ms_usuarios.dto.LoginRequest;
import com.fullstack3.ms_usuarios.dto.LoginResponse;
import com.fullstack3.ms_usuarios.model.Usuario;
import com.fullstack3.ms_usuarios.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public Usuario guardar(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }

        if (usuario.getActivo() == null) {
            usuario.setActivo(1);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        Usuario usuario = buscarPorId(id);

        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        usuario.setPassword(usuarioActualizado.getPassword());
        usuario.setRol(usuarioActualizado.getRol());
        usuario.setActivo(usuarioActualizado.getActivo());

        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByCorreoAndPasswordAndActivo(
                        request.getCorreo(),
                        request.getPassword(),
                        1)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas o usuario inactivo"));

        return new LoginResponse(
                "Inicio de sesion correcto",
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getRol()
        );
    }
}
