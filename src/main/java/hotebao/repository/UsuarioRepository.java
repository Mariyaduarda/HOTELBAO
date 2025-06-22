package hotebao.repository;

import hotebao.entity.UsuarioEntity;
import hotebao.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    // buscar usuário por login
    Optional<UsuarioEntity> findByLoginUser(String loginUser);

    // buscar usuário por email
    Optional<UsuarioEntity> findByEmail(String email);

    // buscar usuário por CPF
    Optional<UsuarioEntity> findByCpf(String cpf);

    // buscar usuários por perfil
    List<UsuarioEntity> findByPerfil(UsuarioEntity.PerfilUsuario perfil);

    // buscar usuário por cliente associado
    Optional<UsuarioEntity> findByClienteEntity(ClienteEntity clienteEntity);

    // verificar se login já existe
    boolean existsByLoginUser(String loginUser);

    // verificar se email já existe
    boolean existsByEmail(String email);

    // verificar se CPF já existe
    boolean existsByCpf(String cpf);

    // buscar apenas administradores
    List<UsuarioEntity> findByPerfilOrderByLoginUserAsc(UsuarioEntity.PerfilUsuario perfil);
}