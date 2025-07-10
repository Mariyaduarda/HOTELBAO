package hotebao.repository;

import hotebao.entity.ClienteEntity;
import hotebao.entity.EstadiaEntity;
import hotebao.entity.QuartoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstadiaRepository extends JpaRepository<EstadiaEntity, Long> {


    @Query("SELECT e FROM EstadiaEntity e WHERE e.confirmationCode = :code")
    Optional<EstadiaEntity> findEstadiaEntityConfirmationCode(@Param("code") String confirmationCode);

    // Find by quarto (correct property name)
    List<EstadiaEntity> findByQuarto(QuartoEntity quarto);

    // Find by cliente (correct property name)
    List<EstadiaEntity> findByCliente(ClienteEntity cliente);

    // Find by data entrada
    List<EstadiaEntity> findByDataEntrada(LocalDate dataEntrada);

    // Find by data saida
    List<EstadiaEntity> findByDataSaida(LocalDate dataSaida);

    // If you need sorted results
    List<EstadiaEntity> findByQuarto(QuartoEntity quarto, Sort sort);
    List<EstadiaEntity> findByCliente(ClienteEntity cliente, Sort sort);

    Optional<Object> findByConfirmationCode(String confirmationCode);
}