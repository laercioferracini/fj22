package br.com.ferracini.argentum.indicadores;

import br.com.ferracini.argentum.modelo.SerieTemporal;

import java.math.BigDecimal;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>04/06/2020</pre>
 */
public interface Indicador {
    //Desing patterns Strategy
    //BigDecimal calcula(int posicao, SerieTemporal serie);
    BigDecimal calcula(int posicao, SerieTemporal serie, int intervalo);
}
