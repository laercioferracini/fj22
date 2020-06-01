package br.com.ferracini.argentum.indicadores;

import br.com.ferracini.argentum.modelo.SerieTemporal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>29/05/2020</pre>
 */
public class MediaMovelSimples {

    public BigDecimal calcula(int posicao, SerieTemporal serie) {

        BigDecimal soma = BigDecimal.ZERO;
        for (int i = posicao; i > posicao - 3; i--) {
            soma = soma.add(serie.getCandle(i).getFechamento());
        }
        return soma.divide(BigDecimal.valueOf(3), RoundingMode.HALF_DOWN);
    }
}
