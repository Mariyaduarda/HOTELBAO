package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstadiaDTO {

    private int id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private BigDecimal valorTotal;
    private List<EstadiaDTO> estadias;
    private UsuarioDTO usuario;
    private QuartoDTO quarto;
    private ClienteDTO cliente;

    private List<EstadiaDTO> estadia = new ArrayList<>();

    @Override
    // me fornece infos legiveis de um objeto convertido à string
    public String toString() {
        return "EstadiaDTO{" +
                "id=" + id +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", valorTotal=" + valorTotal +
                ", estadias=" + estadias +
                ", usuario=" + usuario +
                ", quarto=" + quarto +
                ", cliente=" + cliente +
                '}';
    }
}
