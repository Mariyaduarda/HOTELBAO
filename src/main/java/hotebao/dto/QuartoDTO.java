package hotebao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hotebao.utils.Utils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuartoDTO {

    private Long idQuarto;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String imagem;

    // conectando a estadias
    private List<EstadiaDTO> estadia = new ArrayList<>();
    private String sessionToken;
    private long tokenExpirationTime;

    //criar metodo ou inline para gerar meus tokens do quartoDTO
    public void generateSessionToken() {
        this.sessionToken = Utils.generateString(40);
        this.tokenExpirationTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
    }
}
