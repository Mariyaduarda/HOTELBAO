package hotebao.service;

import hotebao.exception.OurException;
import hotebao.repository.UsuarioRepository;
import hotebao.entity.UsuarioEntity;
import hotebao.security.JWTUtil;
import hotebao.service.past.InterfaceUsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements InterfaceUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response registro(UsuarioEntity usuarioEntity){
        Response response = new Response();

        try{
            if (usuarioEntity.getPerfil() == null){
                usuarioEntity.setPerfil(UsuarioEntity.PerfilUsuario.USUARIO_ROLE));
            }
            if (usuarioRepository.existsByEmail(usuarioEntity.getEmail())){
                throw new OurException(usuarioEntity.getEmail() + " " + "Já existe no sistema");
            }

        }catch (OurException e){

        }catch (Exception e){

        }
        return response;
    }
}