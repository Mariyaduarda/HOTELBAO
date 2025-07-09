package hotebao.controller;

import hotebao.dto.Response;
import hotebao.entity.EstadiaEntity;
import hotebao.service.interf.InterfaceEstadiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estadias")
public class EstadiaController {

    @Autowired
    private InterfaceEstadiaService interfaceEstadiaService;

    @PostMapping("/estadia-quarto/{idQuarto}/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE') or hasAnyAuthority('USUARIO_ROLE')")
    public ResponseEntity<Response> EstadiaQuarto(
            @PathVariable Long idQuarto,
            @PathVariable Long idUsuario,
            @RequestBody EstadiaEntity estadiaRequest
    ) {
        Response response = interfaceEstadiaService.SalvaEstadia(idQuarto, idUsuario, estadiaRequest);
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    public ResponseEntity<Response> getAllEstadias() {
        Response response = interfaceEstadiaService.getAllEstadias();
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    public ResponseEntity<Response> getEstadiaById(@PathVariable Long id) {
        Response response = interfaceEstadiaService.getAllEstadia(id);
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @GetMapping("/confirmation/{confirmationCode}")
    @PreAuthorize("hasAnyAuthority('ADMIN_ROLE')")
    public ResponseEntity<Response> getEstadiaByConfirmationCode(@PathVariable String confirmationCode) {
        Response response = interfaceEstadiaService.getEstadiaByConfirmationCode(confirmationCode);
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }
}