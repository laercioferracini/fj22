package br.com.ferracini.argentum.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
public class Candlestick {

    private final double abertura;
    private final double fechamento;
    private final double minimo;
    private final double maximo;
    private final double volume;
    private final LocalDateTime data;

    public Candlestick(double abertura, double fechamento, double minimo, double maximo, double volume, LocalDateTime data) {
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.minimo = minimo;
        this.maximo = maximo;
        this.volume = volume;
        this.data = data;
    }

    public double getAbertura() {
        return abertura;
    }

    public double getFechamento() {
        return fechamento;
    }

    public double getMinimo() {
        return minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public double getVolume() {
        return volume;
    }

    public LocalDateTime getData() {
        return data;
    }
    public boolean isAlta(){
        return this.abertura < this.fechamento;
    }
    public boolean isBaixa(){
        return this.abertura > this.fechamento;
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
