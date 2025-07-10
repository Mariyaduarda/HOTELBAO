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

    // 1. Buscar tipos distintos de quarto
    @Query("SELECT DISTINCT q.tipoQuarto FROM QuartoEntity q")
    List<QuartoEntity.TipoQuarto> findDistinctTiposQuarto();

    // 2. Quartos disponíveis (sem estadias ativas)
    @Query("SELECT q FROM QuartoEntity q WHERE q.id NOT IN " +
            "(SELECT e.quarto.id FROM EstadiaEntity e " +
            "WHERE (:data BETWEEN e.dataEntrada AND e.dataSaida))")
    List<QuartoEntity> findQuartosDisponiveis(@Param("data") LocalDate data);

    // 3. Quartos disponíveis por tipo e período
    @Query("SELECT q FROM QuartoEntity q WHERE " +
            "q.tipoQuarto = :tipo AND " +
            "NOT EXISTS (" +
            "    SELECT e FROM EstadiaEntity e " +
            "    WHERE e.quarto = q AND " +
            "    (e.dataEntrada < :dataSaida AND e.dataSaida > :dataEntrada)" +
            ")")
    List<QuartoEntity> findQuartosDisponiveisPorTipoEPeriodo(
            @Param("dataEntrada") LocalDate dataEntrada,
            @Param("dataSaida") LocalDate dataSaida,
            @Param("tipo") QuartoEntity.TipoQuarto tipo);

    // Métodos derivados
    List<QuartoEntity> findByTipoQuarto(QuartoEntity.TipoQuarto tipo);
    List<QuartoEntity> findByNomeContainingIgnoreCase(String nome);
    Optional<QuartoEntity> findByImagem(String imagem);

    List<QuartoEntity> findQuartosDisponiveisByDateAndType(LocalDate dataEntrada, LocalDate dataSaida, String s);

    List<QuartoEntity> getDisponibilidadeGeral();
}