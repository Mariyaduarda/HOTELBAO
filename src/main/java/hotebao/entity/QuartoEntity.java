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

    @OneToMany(mappedBy = "quarto")
    private Set<EstadiaEntity> estadias = new LinkedHashSet<>();

    // enum declarado tipo de quartos numa classe
    public enum TipoQuarto {
        STANDARD("Standard"),
        SUITE("Suíte"),
        DELUXE("Deluxe");

        private final String descricao;

        TipoQuarto(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

        public boolean isEmpty() {
            return this == STANDARD;
        }

        public boolean isBlank() {
            return this == STANDARD;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarto")  // Make sure this matches your @JoinColumn reference
    private Long id;

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
        this.id = idquarto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
        this.tipoQuarto = tipoQuarto;
    }
}