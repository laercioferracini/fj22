package br.com.ferracini.argentum.modelo;

import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>29/05/2020</pre>
 */
public class SerieTemporal {

    private final List<Candle> candles;

    public SerieTemporal(List<Candle> candles) {
        if (candles ==null) throw new IllegalArgumentException("Lista não pode ser nula");
        this.candles = candles;
    }

    public Candle getCandle(int i) {
        return this.candles.get(i);
    }

    public int getUltimaPosicao(){
        return this.candles.size() - 1;
    }

}
