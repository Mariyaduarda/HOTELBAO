package hotebao.service;

import hotebao.entity.UsuarioEntity;
import hotebao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
    private UsuarioRepository usuarioRepository;
    public UserDetails loadUserByUsername(String nusername) throws UsernameNotFoundException {
        return (UserDetails) usuarioRepository.findByEmail(nusername).orElseThrow(()-> new UsernameNotFoundException("username nao encontrado"));
    }

}