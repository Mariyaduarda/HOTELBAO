package hotebao.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @Column(nullable = false, unique = true)
    private String loginUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil;

    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "cliente_codigo")
    private ClienteEntity clienteEntity;

    public UsuarioEntity() {
    }

    public UsuarioEntity(long idUser, String loginUser, PerfilUsuario perfil, String senha, ClienteEntity clienteEntity) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.perfil = perfil;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.clienteEntity = clienteEntity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ClienteEntity getCliente() {
        return clienteEntity;
    }

    public void setCliente(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public enum PerfilUsuario {
        NAO_AUTENTICADO,
        CLIENTE,
        ADMIN
    }
}
