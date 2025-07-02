package hotebao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @Column(nullable = false, unique = true)
    private String loginUser;

    // enum para admin ou user
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil;

    //anotação not blank para campos obrigatórios
    @NotBlank(message = "Email é campo obrigatório")
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha é campo obrigatório")
    @Column(nullable = false)
    private String senha;

    @NotBlank(message = "CPF é campo obrigatório")
    @Column(nullable = false, unique = true)
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
