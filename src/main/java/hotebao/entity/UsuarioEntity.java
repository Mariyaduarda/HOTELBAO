package hotebao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Setter;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.IMessageContext;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUser;

    @NotBlank (message = "Obrigatório inserir username")
    @Size(min = 5, max = 30, message = "Username deve ter entre 3 a 30 caracteres")
    @Column(name ="login_user", nullable = false, unique = true, length = 30)
    private String loginUser;

    // enum para admin ou user
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilUsuario perfil = PerfilUsuario.CLIENTE;

    //anotação not blank para campos obrigatórios
    @NotBlank(message = "Email é campo obrigatório")
    @Email(message = "Email deve ter formato válido")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Senha é campo obrigatório")
    @Column(nullable = false)
    private String senha;

    @NotBlank(message = "CPF é campo obrigatório")
    @CPF(message = "Por favor, digite CPF de formato válido, sem espaços")
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    //linkando usuario a aestadia
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EstadiaEntity> estadias = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "cliente_codigo")
    private ClienteEntity clienteEntity;

    public UsuarioEntity() {
    }


    public enum PerfilUsuario {
        NAO_AUTENTICADO,
        CLIENTE,
        ADMIN
    }

}
