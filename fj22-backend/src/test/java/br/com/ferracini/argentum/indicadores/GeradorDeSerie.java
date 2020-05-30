package br.com.ferracini.argentum.indicadores;

import br.com.ferracini.argentum.modelo.Candlestick;
import br.com.ferracini.argentum.modelo.SerieTemporal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>29/05/2020</pre>
 */
public class GeradorDeSerie {
    /**
     * Serve para ajudar a fazer os testes.
     * <p>
     * Recebe uma sequência de valores e cria candles com abertura, fechamento,
     * minimo e maximo iguais, mil de volume e data de hoje. Finalmente, devolve
     * tais candles encapsuladas em uma Serie Temporal.
     **/
    public static SerieTemporal criaSerie(double... valores) {
        List<Candlestick> candles = new ArrayList<>();
        for (double d : valores) {
            BigDecimal dd = BigDecimal.valueOf(d);
            candles.add(new Candlestick(dd, dd, dd, dd,
                    BigDecimal.valueOf(1000), Calendar.getInstance()));
        }
        return new SerieTemporal(candles);
    }
}
