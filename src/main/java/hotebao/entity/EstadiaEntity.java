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
    @Column(name = "id_estadia")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private UsuarioEntity usuario;

    @Column(name = "confirmation_code", unique = true, length = 50)
    private String confirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false, referencedColumnName = "id_cliente")
    private ClienteEntity cliente;

    @NotNull(message = "Quarto é obrigatório!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarto_id", nullable = false, referencedColumnName = "id_quarto")
    private QuartoEntity quarto;

    @NotNull(message = "Data de entrada é obrigatória!")
    @Column(nullable = false)
    private LocalDate dataEntrada;

    @NotNull(message = "Data de saída é obrigatória!")
    @Column(nullable = false)
    private LocalDate dataSaida;

    @NotNull(message = "Valor total é obrigatório!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser maior que zero!")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotal;

    public long getIdEstadia() {
        return id;
    }
}