package hotebao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table (name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcliente;

    @Setter
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "CPF é campo obrigatório")
    @Setter
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank(message = "Email é campo obrigatório")
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    private String telefone;

    @Setter
    @Column(nullable = false, unique = true)
    private String login;

    @NotBlank(message = "Senha é campo obrigatório")
    @Setter
    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "clienteCodigo")
    private Set<EstadiaEntity> estadias = new LinkedHashSet<>();

    @OneToOne(mappedBy = "clienteEntity")
    private UsuarioEntity usuario;

    public ClienteEntity(long idcliente, String nome, String email, String telefone, String login, String senha) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
    }

    public ClienteEntity() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "idcliente=" + idcliente +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
