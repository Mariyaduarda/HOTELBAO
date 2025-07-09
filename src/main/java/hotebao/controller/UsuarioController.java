package hotebao.controller;

import hotebao.dto.Response;
import hotebao.service.interf.InterfaceUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private InterfaceUsuarioService usuarioService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<Response> getAllUsuarios(){
        Response response = usuarioService.getAllUsuarios();
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @GetMapping("/get-by-id{idUsuario}")
    public ResponseEntity<Response> getUsuariosbyId(@PathVariable("idUsuario") Long usuarioId){
        Response response = usuarioService.getUsuarioById(Long.valueOf(usuarioId));
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @GetMapping("/delete/{idUsuario}")
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<Response> deleteUsuario( @PathVariable("idUsuario") Long idUsuario){
        Response response = usuarioService.deleteUsuario(Long.valueOf(idUsuario));
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }
    @GetMapping("/get-logged-in-profile-info")
    public ResponseEntity<Response> getLoggedInUsuarioProfileInfo(){

        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String email = authenticate.getName();
        Response response = usuarioService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @GetMapping("/get-usuario-estadia/{idUsuario}")
    public ResponseEntity<Response> getUsuarioEstadiaHistory(@PathVariable("idUsuario") Long idUsuario){

        Response response = usuarioService.getUsuarioEstadiaHistory(String.valueOf(idUsuario));
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }
}

