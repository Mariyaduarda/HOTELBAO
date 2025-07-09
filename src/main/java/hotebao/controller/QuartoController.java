package hotebao.controller;

import hotebao.dto.Response;
import hotebao.entity.QuartoEntity;
import hotebao.service.interf.InterfaceEstadiaService;
import hotebao.service.interf.InterfaceQuartoService;
import hotebao.service.obj.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    private InterfaceQuartoService interfacequartoService;

    @Autowired
    private InterfaceEstadiaService interfaceEstadiaService;

    @Autowired
    private QuartoService quartoService;

    @PostMapping("/add")
    public ResponseEntity<Response> addNovoQuarto(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "tipoQuarto", required = false) QuartoEntity.TipoQuarto tipoQuarto,
            @RequestParam(value = "preco", required = false) BigDecimal preco,
            @RequestParam(value = "descricao", required = false) String descricao
    ) throws Exception {

        if (image == null || image.isEmpty() || tipoQuarto == null || preco == null ||
                descricao == null || descricao.isEmpty()) {
            Response response = new Response();
            response.setStatusCodigo(400);
            response.setMessage("Erro ao adicionar novo quarto. Ausência de valores para todos os campos");
            return ResponseEntity.status(response.getStatusCodigo()).body(response);
        }

        Response response = quartoService.addNovoQuarto(image, tipoQuarto, preco, descricao);
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllQuartos() {
        Response response = quartoService.getAllQuartos();
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    //URL mapping e retorna tipe
    @GetMapping("/tipos")
    public ResponseEntity<List<String>> getTipoQuartos() {
        // chama um methodo e retorna tipes,
        List<String> tipos = quartoService.getTipoQuartos(); // You'll need to implement this method
        return ResponseEntity.ok(tipos);
    }

    // URL mapping
    @GetMapping("/quarto-by-id/{idQuarto}")
    public ResponseEntity<Response> getQuartoById(@PathVariable("idQuarto") Long idQuarto) {
        Response response = quartoService.getQuartoById(idQuarto);
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    // URL mapping e logica de parametro
    @GetMapping("/all-disponivel-quartos")
    public ResponseEntity<Response> getQuartosDisponiveis() {
        Response response = quartoService.getQuartosDisponiveis();
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @GetMapping("/all-disponivel-quartos-by-date-and-type")
    public ResponseEntity<Response> getDisponibilidadeGeral(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntrada,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataSaida,
            @RequestParam(required = false) QuartoEntity.TipoQuarto tipoQuarto
    ) {
        if (dataEntrada == null || dataSaida == null || tipoQuarto.isBlank()) {
            Response response = new Response();
            Response.setStatusCode(400);
            response.setMessage("Todos os campos devem ser preenchidos");
        }

        Response response = quartoService.getDisponibilidadeGeral(dataEntrada, dataSaida, tipoQuarto);
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }
    @PostMapping("/update/(idQuarto)")
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<Response> updateQuarto(
            @PathVariable Long idQuarto,
        @RequestParam(value = "image", required = false) MultipartFile image,
        @RequestParam(value = "tipoQuarto", required = false) QuartoEntity.TipoQuarto tipoQuarto,
        @RequestParam(value = "preco", required = false) BigDecimal preco,
        @RequestParam(value = "descricao", required = false) String descricao) throws Exception {
        Response response = quartoService.updateQuarto(idQuarto, descricao, tipoQuarto, preco, image);

        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }

    @DeleteMapping("/delete/{idQuarto")
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<Response> deleteQuarto(@PathVariable Long idQuarto) {
        Response response = quartoService.deleteQuarto(idQuarto);
        return ResponseEntity.status(response.getStatusCodigo()).body(response);
    }
}