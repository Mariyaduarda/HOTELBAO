package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hotebao.entity.UsuarioEntity;
import hotebao.utils.Utils;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

        private long idCliente;
        private LocalDate dataEntrada;
        private LocalDate dataSaida;
        private BigDecimal valorTotal;
        private List<hotebao.dto.EstadiaDTO> estadias;
        private UsuarioDTO usuario;
        private QuartoDTO quarto;
        private ClienteDTO cliente;
        private String nome;
        private String email;
        private String senha;
        private UsuarioEntity.PerfilUsuario perfilUsuario;
        private String telefone;
        private String cpf;

        private List<EstadiaDTO> estadia = new ArrayList<>();
        private String sessionToken;
        private long tokenExpirationTime;

        //criar metodo ou inline para gerar meus tokens do usuarioDTO
        public void generateSessionToken() {
                this.sessionToken = Utils.generateString(40);
                this.tokenExpirationTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
        }
}


