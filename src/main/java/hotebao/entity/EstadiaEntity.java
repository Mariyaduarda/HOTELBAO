package hotebao.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "estadias")
public class EstadiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstadia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_codigo", nullable = false)
    private ClienteEntity clienteEntityRelacionamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarto_codigo", nullable = false)
    private QuartoEntity quartoEntityRelacionamento;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    @Column(nullable = false)
    private LocalDate dataSaida;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    public EstadiaEntity(int idEstadia, ClienteEntity clienteEntityRelacionamento, QuartoEntity quartoEntityRelacionamento, LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valorTotal) {
        this.idEstadia = idEstadia;
        this.clienteEntityRelacionamento = clienteEntityRelacionamento;
        this.quartoEntityRelacionamento = quartoEntityRelacionamento;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = valorTotal;
    }

    public EstadiaEntity() {

    }

    public int getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(int idEstadia) {
        this.idEstadia = idEstadia;
    }

    public ClienteEntity getClienteRelacionamento() {
        return clienteEntityRelacionamento;
    }

    public void setClienteRelacionamento(ClienteEntity clienteEntityRelacionamento) {
        this.clienteEntityRelacionamento = clienteEntityRelacionamento;
    }

    public QuartoEntity getQuartoRelacionamento() {
        return quartoEntityRelacionamento;
    }

    public void setQuartoRelacionamento(QuartoEntity quartoEntityRelacionamento) {
        this.quartoEntityRelacionamento = quartoEntityRelacionamento;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
