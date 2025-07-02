package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {

        private int id;
        private LocalDate dataEntrada;
        private LocalDate dataSaida;
        private BigDecimal valorTotal;
        private List<hotebao.dto.EstadiaDTO> estadias;
        private UsuarioDTO usuario;
        private QuartoDTO quarto;
        private ClienteDTO cliente;

        private List<EstadiaDTO> estadia = new ArrayList<>();

    }


