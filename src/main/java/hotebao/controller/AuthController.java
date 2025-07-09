package hotebao.controller;

import hotebao.dto.Response;
import hotebao.entity.UsuarioEntity;
import hotebao.service.AuthService;
import hotebao.service.obj.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<Response> registro(@RequestBody UsuarioEntity usuario){
        try {
            Response response = authService.registro(usuario);
            return ResponseEntity.status(response.getStatusCodigo()).body(response);
        } catch (Exception e) {
            Response errorResponse = new Response();
            errorResponse.setStatusCodigo(500);
            errorResponse.setMessage("Erro interno do servidor: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UsuarioEntity loginRequest){
        try {
            Response response = authService.login(loginRequest);
            return ResponseEntity.status(response.getStatusCodigo()).body(response);
        } catch (Exception e) {
            Response errorResponse = new Response();
            errorResponse.setStatusCodigo(500);
            errorResponse.setMessage("Erro interno do servidor: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
