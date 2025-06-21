package hotebao.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "estadias")
public class Estadia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstadia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_codigo", nullable = false)
    private Cliente clienteRelacionamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarto_codigo", nullable = false)
    private Quarto quartoRelacionamento;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    @Column(nullable = false)
    private LocalDate dataSaida;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    public Estadia(int idEstadia, Cliente clienteRelacionamento, Quarto quartoRelacionamento, LocalDate dataEntrada, LocalDate dataSaida, BigDecimal valorTotal) {
        this.idEstadia = idEstadia;
        this.clienteRelacionamento = clienteRelacionamento;
        this.quartoRelacionamento = quartoRelacionamento;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorTotal = valorTotal;
    }

    public Estadia() {

    }

    public int getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(int idEstadia) {
        this.idEstadia = idEstadia;
    }

    public Cliente getClienteRelacionamento() {
        return clienteRelacionamento;
    }

    public void setClienteRelacionamento(Cliente clienteRelacionamento) {
        this.clienteRelacionamento = clienteRelacionamento;
    }

    public Quarto getQuartoRelacionamento() {
        return quartoRelacionamento;
    }

    public void setQuartoRelacionamento(Quarto quartoRelacionamento) {
        this.quartoRelacionamento = quartoRelacionamento;
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
