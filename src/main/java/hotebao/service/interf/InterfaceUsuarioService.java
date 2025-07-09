package hotebao.service.interf;

import hotebao.dto.Response;
import hotebao.entity.UsuarioEntity;

public interface InterfaceUsuarioService {

    // método de registro - agora retorna hotebao.dto.Response
    Response registro(UsuarioEntity usuarioEntity);

    Response login(String email, String senha);

    Response getAllUsuarios();

    Response getUsuarioById(Long id);

    Response deleteUsuario(Long id);
}