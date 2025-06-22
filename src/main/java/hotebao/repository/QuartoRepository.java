package hotebao.repository;

import hotebao.entity.QuartoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuartoRepository extends JpaRepository<QuartoEntity, Long> {

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

}