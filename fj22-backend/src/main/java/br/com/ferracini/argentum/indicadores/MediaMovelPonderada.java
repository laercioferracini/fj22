package br.com.ferracini.argentum.indicadores;

import br.com.ferracini.argentum.modelo.Candle;
import br.com.ferracini.argentum.modelo.SerieTemporal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>30/05/2020</pre>
 */
public class MediaMovelPonderada {

    public BigDecimal calcula(int posicao, SerieTemporal serie) {
        BigDecimal soma = BigDecimal.ZERO;
        int peso = 3;
        for (int i = posicao; i > posicao - 3; i--) {
            Candle c = serie.getCandle(i);
            soma = soma.add(c.getFechamento().multiply(BigDecimal.valueOf(peso)));
            peso--;
        }
        return soma.divide(BigDecimal.valueOf(6), RoundingMode.HALF_DOWN);
    }

}
