package hotebao.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @Column(nullable = false, unique = true)
    private String loginUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil;

    @Column(nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "cliente_codigo")
    private Cliente cliente;

    public Usuario() {
    }

    public Usuario(long idUser, String loginUser, PerfilUsuario perfil, String senha, Cliente cliente) {
        this.idUser = idUser;
        this.loginUser = loginUser;
        this.perfil = perfil;
        this.senha = senha;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public enum PerfilUsuario {
        NAO_AUTENTICADO,
        CLIENTE,
        ADMIN
    }
}
