package hotebao.repository;

import hotebao.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<QuartoEntity, Long> {

    @Query("SELECT DISTINCT r.TipoQuarto FROM QuartoEntity r")
    List<String> findDistinctQuartoType();

    @Query("SELECT r FROM QuartoEntity r WHERE r.idquarto NOT IN (SELECT b.quartoCodigo FROM EstadiaEntity b)")
    List<QuartoEntity> findQuartosDisponiveis();

    // buscar quartos por nome
    List<QuartoEntity> findByNome(String nome);

    // buscar quartos que contenham parte do nome - busca flexível)
    List<QuartoEntity> findByNomeContainingIgnoreCase(String nome);

    // buscar quartos por descrição
    List<QuartoEntity> findByDescricao(String descricao);

    // buscar quartos que contenham parte da descrição
    List<QuartoEntity> findByDescricaoContainingIgnoreCase(String descricao);

    // buscar quartos por preço exato
    List<QuartoEntity> findByPreco(BigDecimal preco);

    // buscar quarto por imagem
    Optional<QuartoEntity> findByImagem(String imagem);

    @Query("SELECT r FROM QuartoEntity r WHERE " +
            "r.tipoQuarto = :tipoQuarto AND " +
            "r.idquarto NOT IN (" +
            "    SELECT e.QuartoEntity.idquarto FROM EstadiaEntity e " +
            "    WHERE (e.dataEntrada <= :dataSaida AND e.dataSaida >= :dataEntrada)" +
            ")")
    List<QuartoEntity> findQuartosDisponiveisByDateAndType(
            @Param("dataEntrada") LocalDate dataEntrada,
            @Param("dataSaida") LocalDate dataSaida,
            @Param("tipoQuarto") String tipoQuarto);

    @Query("SELECT r FROM QuartoEntity r WHERE r.idquarto NOT IN (SELECT b.quartoCodigo FROM EstadiaEntity b)")
    List<QuartoEntity> getDisponibilidadeGeral();

    List<QuartoEntity> findByTipoQuarto(QuartoEntity.TipoQuarto quartoTipo);
}