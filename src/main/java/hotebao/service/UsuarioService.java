package hotebao.service;

import hotebao.repository.UsuarioRepository;
import hotebao.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String loginUser) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByEmail(loginUser)
                .orElseThrow(() -> new UsernameNotFoundException("O nome de usuário não foi encontrado."));

        // Opção 1: Se UsuarioEntity implementa UserDetails
        return (UserDetails) usuario;

        // Opção 2: Se usando CustomUserDetails (descomente a linha abaixo e comente a de cima)
        // return new CustomUserDetails(usuario);
    }

    // Método adicional se você quiser buscar usuário por email
    public UsuarioEntity findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
    }
}