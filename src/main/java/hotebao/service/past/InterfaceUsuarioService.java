package hotebao.service.past;

import hotebao.entity.UsuarioEntity;
import org.apache.coyote.Response;

public interface InterfaceUsuarioService {
    Response registro(UsuarioEntity usuarioEntity);
}
