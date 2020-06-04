package br.com.ferracini.argentum.indicadores;

import br.com.ferracini.argentum.modelo.SerieTemporal;

import java.math.BigDecimal;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>04/06/2020</pre>
 */
public class IndicadorFechamento implements Indicador{

    @Override
    public BigDecimal calcula(int posicao, SerieTemporal serie) {
        return serie.getCandle(posicao).getFechamento();
    }

    @Override
    public BigDecimal calcula(int posicao, SerieTemporal serie, int intervalo) {
        return serie.getCandle(posicao).getFechamento();
    }

    @Override
    public String toString() {
        return "Fechamento";
    }
}
