package hotebao.service.interf;

import hotebao.dto.Response;
import hotebao.entity.QuartoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface InterfaceQuartoService {

    Response addNovoQuarto (MultipartFile imagem, QuartoEntity.TipoQuarto tipoQuarto, BigDecimal preco, String descricao) throws Exception;
    List<String> getQuartosByTipo (QuartoEntity.TipoQuarto tipo);

    Response getAllQuartos();

    Response deleteQuarto (long idQuarto);

    Response updateQuarto (long idQuarto, String descricao, QuartoEntity.TipoQuarto tipoQuarto,BigDecimal preco, MultipartFile imagem) throws Exception;

    Response getQuartoById(long idQuarto);

    Response getDisponibilidadeById(long idQuarto, LocalDate dataEntrada, LocalDate dataSaida);

    // buscar quartos disponíveis em um período
    Response getQuartosDisponiveis();

    // verificar disponibilidade geral (sem datas específicas)
    Response getDisponibilidadeGeral(LocalDate dataEntrada, LocalDate dataSaida, QuartoEntity.TipoQuarto tipoQuarto);
}
