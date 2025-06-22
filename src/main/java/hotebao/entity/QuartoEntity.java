package hotebao.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "quarto")
public class QuartoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idquarto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    private String imagem;

    public QuartoEntity(int idquarto, String nome, String descricao, BigDecimal preco, String imagem) {
        this.idquarto = idquarto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
    }

    public QuartoEntity() {

    }

    public int getIdquarto() {
        return idquarto;
    }

    public void setIdquarto(int idquarto) {
        this.idquarto = idquarto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
