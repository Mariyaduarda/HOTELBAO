package hotebao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "estadias")
@NoArgsConstructor
@AllArgsConstructor
public class EstadiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstadia;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @NotNull(message = "Cliente é obrigatório!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @NotNull(message = "Quarto é obrigatório!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarto_id", nullable = false)
    private QuartoEntity quarto;

    @NotNull(message = "Data de entrada é obrigatória!")
    @Column(nullable = false)
    private LocalDate dataEntrada;

    @NotNull(message = "Data de saída é obrigatória!")
    @Column(nullable = false)
    private LocalDate dataSaida;

    @NotNull(message = "Valor total é obrigatório!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser maior que zero!")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    // Construtor customizado (se necessário)
    public EstadiaEntity(ClienteEntity cliente, QuartoEntity quarto, LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = valorTotal;
    }

}