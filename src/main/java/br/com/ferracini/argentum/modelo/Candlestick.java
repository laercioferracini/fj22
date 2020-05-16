package br.com.ferracini.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
public class Candlestick {

    private final BigDecimal abertura;
    private final BigDecimal fechamento;
    private final BigDecimal minimo;
    private final BigDecimal maximo;
    private final BigDecimal volume;
    private final LocalDateTime data;

    public Candlestick(BigDecimal abertura, BigDecimal fechamento, BigDecimal minimo, BigDecimal maximo, BigDecimal volume, LocalDateTime data) {
        if (maximo.compareTo(minimo)< 0) throw new IllegalArgumentException("Valor máximo não poder ser menor que o valor mínimo");
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.minimo = minimo;
        this.maximo = maximo;
        this.volume = volume;
        this.data = data;
    }

    public BigDecimal getAbertura() {
        return abertura;
    }

    public BigDecimal getFechamento() {
        return fechamento;
    }

    public BigDecimal getMinimo() {
        return minimo;
    }

    public BigDecimal getMaximo() {
        return maximo;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public LocalDateTime getData() {
        return data;
    }

    public boolean isAlta() {
        return this.abertura.compareTo(this.fechamento) < 0;
    }

    public boolean isBaixa() {
        return this.abertura.compareTo(this.fechamento) > 0;
    }

    @Override
    public String toString() {
        return "Candle [" +
                "Abertura: " + abertura +
                ", Fechamento: " + fechamento +
                ", Minimo: " + minimo +
                ", Maximo: " + maximo +
                ", Volume: " + volume +
                ", Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ']';
    }
}
