package br.com.ferracini.argentum.indicadores;

import br.com.ferracini.argentum.modelo.SerieTemporal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>29/05/2020</pre>
 */
public class MediaMovelSimples implements Indicador {

    @Override
    public BigDecimal calcula(int posicao, SerieTemporal serie) {
        BigDecimal soma = BigDecimal.ZERO;

        for (int i = posicao; i > posicao - 3; i--) {
            if (i < 0) break;
            soma = soma.add(serie.getCandle(i).getFechamento());

        }
        return soma.divide(BigDecimal.valueOf(3), RoundingMode.HALF_DOWN);
    }

    public BigDecimal calcula(int posicao, SerieTemporal serie, int intervalo) {
        if (intervalo < 0) throw new IllegalArgumentException("Valor menor que zero");
        BigDecimal soma = BigDecimal.ZERO;

        for (int i = posicao; i > posicao - intervalo; i--) {
            if (i < 0) break;
            soma = soma.add(serie.getCandle(i).getFechamento());

        }
        return soma.divide(BigDecimal.valueOf(intervalo), RoundingMode.HALF_DOWN);
    }

    @Override
    public String toString() {
        return "MMS de Fechamento";
    }
}
