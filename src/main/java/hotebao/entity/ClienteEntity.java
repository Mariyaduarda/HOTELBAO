package hotebao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "id_cliente") // Nome da coluna no banco de dados
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "cliente")
    private Set<EstadiaEntity> estadias = new LinkedHashSet<>();

    @Size(max = 255)
    @NotNull
    @Column(name = "login_user", nullable = false)
    private String loginUser;

    @NotNull
    @Lob
    @Column(name = "perfil", nullable = false)
    private String perfil;

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "idCliente=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
