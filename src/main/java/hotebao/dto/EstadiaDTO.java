package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hotebao.utils.Utils;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstadiaDTO {

    private long idEstadia;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private BigDecimal valorTotal;
    private List<EstadiaDTO> estadias;
    private UsuarioDTO usuario;
    private QuartoDTO quarto;
    private ClienteDTO cliente;
    private String ConfirmationCode;

    private List<EstadiaDTO> estadia = new ArrayList<>();
    private long tokenExpirationTime;
    private String sessionToken;

    @Override
    // me fornece infos legiveis de um objeto convertido à string
    public String toString() {
        return "EstadiaDTO{" +
                "id=" + idEstadia +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", valorTotal=" + valorTotal +
                ", estadias=" + estadias +
                ", usuario=" + usuario +
                ", quarto=" + quarto +
                ", cliente=" + cliente +
                '}';
    }

    //criar metodo ou inline para gerar meus tokens do usuarioDTO
    public void generateSessionToken() {
        this.sessionToken = Utils.generateString(40);
        this.tokenExpirationTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
    }
}
