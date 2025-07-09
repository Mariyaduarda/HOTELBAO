package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCodigo;
    private String message;

    private String token;
    private String PerfilUsuario;
    private String tempoExpirado;
    private String ConfirmationCode;

    private UsuarioDTO usuarioDTO;
    private QuartoDTO quartoDTO;
    private List<UsuarioDTO> usuarioDTOList;
    private List<QuartoDTO> quartoDTOList;
    private List<EstadiaDTO> estadiaDTOList;

    public static void setStatusCode(int i) {
    }

    public void setEstadiaDTO(EstadiaDTO estadiaDTO) {
    }

    public void setEstadiaEntityList(List<EstadiaDTO> estadiaDTO) {
    }
}
