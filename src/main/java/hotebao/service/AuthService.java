package hotebao.service;

import hotebao.dto.Response;
import hotebao.entity.UsuarioEntity;
import hotebao.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {


    public Response registro(UsuarioEntity usuario) {
        return null;
    }

    public Response login(UsuarioEntity loginRequest) {

    return null;
    }
}

