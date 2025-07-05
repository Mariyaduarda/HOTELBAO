package hotebao.repository;

import hotebao.entity.ClienteEntity;
import hotebao.entity.EstadiaEntity;
import hotebao.entity.QuartoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstadiaRepository extends JpaRepository<EstadiaEntity, Long> {

    Optional<EstadiaEntity> findEstadiaEntityConfirmationCode(String ConfirmationCode);
    // buscar por quarto - cliente
    List<EstadiaEntity> findByQuartoEntityRelacionamento(QuartoEntity quartoEntityRelacionamento);

    // buscar por cliente - quarto
    List<EstadiaEntity> findByClienteEntityRelacionamento(ClienteEntity clienteEntityRelacionamento);

    // buscar por data de entrada
    List<EstadiaEntity> findByDataEntrada(LocalDate dataEntrada);

    // busca por data de saida
    List<EstadiaEntity> findByDataSaida(LocalDate dataSaida);

}
