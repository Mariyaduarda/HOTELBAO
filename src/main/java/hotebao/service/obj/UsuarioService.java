// Use your existing Response DTO class:
// hotebao.dto.Response with fields: statusCodigo, message, token, role, tempoExpirado, etc.

// Updated Service Class
package hotebao.service.obj;

import hotebao.dto.Response;
import hotebao.dto.UsuarioDTO;
import hotebao.exception.OurException;
import hotebao.repository.UsuarioRepository;
import hotebao.entity.UsuarioEntity;
import hotebao.security.JWTService;
import hotebao.security.JWTService;
import hotebao.service.interf.InterfaceUsuarioService;
import hotebao.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public abstract class UsuarioService implements InterfaceUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    private AuthenticationManager authenticationManager;

    @Override
    public Response registro(UsuarioEntity usuarioEntity) {
        Response response = new Response();

        try {
            // Set default role if not provided
            if (usuarioEntity.getPerfil() == null) {
                usuarioEntity.setPerfil(UsuarioEntity.PerfilUsuario.USUARIO_ROLE);
            }

            // Check if email already exists
            if (usuarioRepository.existsByEmail(usuarioEntity.getEmail())) {
                throw new OurException(usuarioEntity.getEmail() + " já existe no sistema");
            }

            // Encode password
            usuarioEntity.setSenha(passwordEncoder.encode(usuarioEntity.getSenha()));

            // Save user
            UsuarioEntity savedUsuario = usuarioRepository.save(usuarioEntity);
            UsuarioDTO userDTO = Utils.mapUsuarioEntityToUsuarioDTO(savedUsuario);

            response.setStatusCodigo(200);
            response.setUsuarioDTO(userDTO);
            response.setMessage("Usuário registrado com sucesso");

        } catch (OurException e) {
            response.setStatusCodigo(400);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro interno: " + e.getMessage());
        }

        return response;
    }

    public Response login(String email, String senha) {
        Response response = new Response();

        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senha)
            );

            // Find user
            UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new OurException("Usuário não encontrado"));

            // Generate JWT token
            String token = jwtService.generateToken((UserDetails) usuario);

            // Map to DTO
            UsuarioDTO userDTO = Utils.mapUsuarioEntityToUsuarioDTO(usuario);

            response.setStatusCodigo(200);
            response.setToken(token);
            response.setPerfilUsuario(usuario.getPerfil().name());
            response.setTempoExpirado("24Hrs");
            response.setUsuarioDTO(userDTO);
            response.setMessage("Login realizado com sucesso");

        } catch (OurException e) {
            response.setStatusCodigo(401);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro interno: " + e.getMessage());
        }

        return response;
    }

    public Response getAllUsuarios() {
        Response response = new Response();

        try {
            List<UsuarioEntity> usuarios = usuarioRepository.findAll();
            List<UsuarioDTO> usuariosDTO = usuarios.stream()
                    .map(Utils::mapUsuarioEntityToUsuarioDTO)
                    .collect(Collectors.toList());

            response.setStatusCodigo(200);
            response.setUsuarioDTOList(usuariosDTO);
            response.setMessage("Usuários recuperados com sucesso");

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro ao recuperar usuários: " + e.getMessage());
        }

        return response;
    }

    public Response getUsuarioById(Long id) {
        Response response = new Response();

        try {
            UsuarioEntity usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new OurException("Usuário não encontrado"));

            UsuarioDTO userDTO = Utils.mapUsuarioEntityToUsuarioDTO(usuario);

            response.setStatusCodigo(200);
            response.setUsuarioDTO(userDTO);
            response.setMessage("Usuário encontrado");

        } catch (OurException e) {
            response.setStatusCodigo(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro interno: " + e.getMessage());
        }

        return response;
    }

    public Response deleteUsuario(Long id) {
        Response response = new Response();

        try {
            UsuarioEntity usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new OurException("Usuário não encontrado"));

            usuarioRepository.delete(usuario);

            response.setStatusCodigo(200);
            response.setMessage("Usuário deletado com sucesso");

        } catch (OurException e) {
            response.setStatusCodigo(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro interno: " + e.getMessage());
        }

        return response;
    }
    public Response getMyInfo(String email) {
        Response response = new Response();

        try {
            UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new OurException("Usuário não encontrado"));

            UsuarioDTO userDTO = Utils.mapUsuarioEntityToUsuarioDTO(usuario);

            response.setStatusCodigo(200);
            response.setUsuarioDTO(userDTO);
            response.setMessage("Informações do usuário recuperadas com sucesso");

        } catch (OurException e) {
            response.setStatusCodigo(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCodigo(500);
            response.setMessage("Erro interno: " + e.getMessage());
        }

        return response;
    }
}