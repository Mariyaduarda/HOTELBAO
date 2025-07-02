package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Respoonse {

    private int statusCodigo;
    private String message;

    private String token;
    private String role;
    private String tempoExpirado;
    private String estadiaCodgioConfirma;

    private UsuarioDTO usuarioDTO;
    private QuartoDTO quartoDTO;
    private List<UsuarioDTO> usuarioDTOList;
    private List<QuartoDTO> quartoDTOList;
    private List<EstadiaDTO> estadiaDTOList;
}
