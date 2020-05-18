package br.com.ferracini.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>14/05/2020</pre>
 */
public class CandleBuilder {
    private BigDecimal abertura;
    private BigDecimal fechamento;
    private BigDecimal minimo;
    private BigDecimal maximo;
    private BigDecimal volume;
    private LocalDateTime data;

    public CandleBuilder comAbertura(BigDecimal abertura) {
        this.abertura = abertura;
        return this;
    }

    public CandleBuilder comFechamento(BigDecimal fechamento) {
        this.fechamento = fechamento;
        return this;
    }

    public CandleBuilder comMinimo(BigDecimal minimo) {
        this.minimo = minimo;
        return this;
    }

    public CandleBuilder comMaximo(BigDecimal maximo) {
        this.maximo = maximo;
        return this;
    }

    public CandleBuilder comVolume(BigDecimal volume) {
        this.volume = volume;
        return this;
    }

    public CandleBuilder comData(LocalDateTime data) {
        this.data = data;
        return this;
    }

    public Candlestick geraCandle() {
        boolean camposNulos = verificaCampos();
        if (!camposNulos) throw new IllegalStateException("candle não pode ser gerado com dados nulos");
        return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
    }

    private boolean verificaCampos() {
        return abertura != null && fechamento != null && minimo != null && maximo != null && volume != null && data != null;
    }
}
