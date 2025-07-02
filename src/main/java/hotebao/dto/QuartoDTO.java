package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuartoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private int preco;
    private int imagem;

    // conectando a estadias
    private List<EstadiaDTO> estadia = new ArrayList<>();
}
