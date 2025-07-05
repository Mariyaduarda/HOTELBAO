package hotebao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table (name = "usuario")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;

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

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
