package hotebao.repository;

import hotebao.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    // buscar cliente por nome
    List<ClienteEntity> findByNome(String nome);

    //buscar cliente por cpf
    List<ClienteEntity> findByCpf(String cpf);

    //buscar cliente por telefone
    List<ClienteEntity> findByTelefone(String telefone);

    //buscar cliente por email e telefone
    List<ClienteEntity> findByEmailAndTelefone(String email, String telefone);

    // para uma busca mais flexivel
    List<ClienteEntity> findbyNomeContainingIgnoreCase(String nome);
}
