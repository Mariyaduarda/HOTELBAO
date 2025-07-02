package hotebao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "quarto")
public class QuartoEntity {

    @OneToMany(mappedBy = "quartoCodigo")
    private Set<EstadiaEntity> estadias = new LinkedHashSet<>();

    // enum declarado tipo de quartos numa classe
    public enum TipoQuarto {
        STANDARD("Standard"),
        SUITE("Suíte"),
        DELUXE("Deluxe"),
        PRESIDENCIAL("Presidencial");

        private final String descricao;

        TipoQuarto(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idquarto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    private String imagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "quarto_tipo")
    private TipoQuarto tipoQuarto;

    @OneToMany(mappedBy = "quarto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EstadiaEntity> estadia = new ArrayList<>();

    public QuartoEntity() {}

    public QuartoEntity(Long idquarto, String nome, String descricao, BigDecimal preco, String imagem, TipoQuarto tipoQuarto) {
        this.idquarto = idquarto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
        this.tipoQuarto = tipoQuarto;
    }
}