package br.com.ferracini.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

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
    private Calendar data;

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

    public CandleBuilder comData(Calendar data) {
        this.data = data;
        return this;
    }

    public Candle geraCandle() {
        boolean camposNulos = verificaCampos();
        if (!camposNulos) throw new IllegalStateException("candle não pode ser gerado com dados nulos");
        return new Candle(abertura, fechamento, minimo, maximo, volume, data);
    }

    private boolean verificaCampos() {
        return abertura != null && fechamento != null && minimo != null && maximo != null && volume != null && data != null;
    }
}
