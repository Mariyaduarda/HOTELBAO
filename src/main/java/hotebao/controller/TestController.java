package hotebao.controller;

import hotebao.entity.UsuarioEntity;
import hotebao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<UsuarioEntity> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuarios")
    public UsuarioEntity createUsuario(@RequestBody UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running!";
    }
}