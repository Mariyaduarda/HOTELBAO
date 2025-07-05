package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hotebao.entity.UsuarioEntity;
import lombok.Data;
import hotebao.utils.Utils;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private long idUser;
    private String loginUser;
    private String email;
    private String senha;
    private UsuarioEntity.PerfilUsuario perfilUsuario;
    private String telefone;
    private String cpf;
    private String role;
    private List<EstadiaDTO> estadia = new ArrayList<>();
    private long tokenExpirationTime;
    private String sessionToken;

    /*criar metodo ou inline para gerar meus tokens do usuarioDTO
    * ao invés de usar o método, posso fazer inline
    * usuarioDTO.setSessionToken(Utils.generateString(40));
    * usuarioDTO.setTokenExpirationTime(System.currentTimeMillis() + (24 * 60 * 60 * 1000));
     */
    public void generateSessionToken() {

        this.sessionToken = Utils.generateString(40);
        this.tokenExpirationTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
    }
}
