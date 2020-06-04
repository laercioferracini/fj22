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
public class MediaMovelPonderada implements Indicador {

    @Override
    public BigDecimal calcula(int posicao, SerieTemporal serie) {
        BigDecimal soma = BigDecimal.ZERO;
        int peso = 3;
        for (int i = posicao; i > posicao - 3; i--) {
            if (i < 0) break;
            Candle c = serie.getCandle(i);
            soma = soma.add(c.getFechamento().multiply(BigDecimal.valueOf(peso)));
            peso--;
        }
        return soma.divide(BigDecimal.valueOf(fatorial(6)), RoundingMode.HALF_DOWN);
    }

    @Override
    public BigDecimal calcula(int posicao, SerieTemporal serie, int intervalo) {
        BigDecimal soma = BigDecimal.ZERO;
        int peso = intervalo;
        for (int i = posicao; i > posicao - intervalo; i--) {
            if (i < 0) break;
            Candle c = serie.getCandle(i);
            soma = soma.add(c.getFechamento().multiply(BigDecimal.valueOf(peso)));
            peso--;
        }
        return soma.divide(BigDecimal.valueOf(fatorial(intervalo)), RoundingMode.HALF_DOWN);
    }

    private long fatorial(int peso) {
        long soma = 0;
        for (int i = peso; i > 0; i--) {
            soma += i;
        }
        return soma;
    }

    @Override
    public String toString() {
        return "MMP de Fechamento";
    }
}
