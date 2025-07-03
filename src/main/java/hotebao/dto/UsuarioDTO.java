package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hotebao.entity.UsuarioEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private int idUser;
    private String loginUser;
    private String email;
    private String senha;
    private UsuarioEntity.PerfilUsuario perfilUsuario;
    private String telefone;
    private String cpf;
    private String role;
    private List<EstadiaDTO> estadia = new ArrayList<>();
}
