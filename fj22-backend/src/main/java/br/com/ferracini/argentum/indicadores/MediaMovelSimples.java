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

    private Indicador indicador;

    public MediaMovelSimples(Indicador outroIndicador) {
        indicador = outroIndicador;
    }

    public MediaMovelSimples() {
    }

    public BigDecimal calcula(int posicao, SerieTemporal serie, int intervalo) {
        if (intervalo < 0) throw new IllegalArgumentException("Valor menor que zero");
        BigDecimal soma = BigDecimal.ZERO;

        for (int i = posicao; i > posicao - intervalo; i--) {
            if (i < 0) break;
            soma = soma.add(indicador.calcula(i, serie, intervalo)); //soma.add(serie.getCandle(i).getFechamento());

        }
        return soma.divide(BigDecimal.valueOf(intervalo), RoundingMode.HALF_DOWN);
    }

    @Override
    public String toString() {
        return "MMS de Fechamento";
    }
}
