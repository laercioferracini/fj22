package br.com.ferracini.argentum.indicadores;

import br.com.ferracini.argentum.modelo.SerieTemporal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>30/05/2020</pre>
 */
class MediaMovelPonderadaTest {

    @Test
    public void sequenciaSimplesDeCandles() {
        SerieTemporal serie =
                GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
        MediaMovelPonderada mmp = new MediaMovelPonderada();
        //ex: calcula(2): 1*1 + 2*2 +3*3 = 14. Divide por 6, da 14/6
        BigDecimal aa = BigDecimal.valueOf(14.0).divide(BigDecimal.valueOf(6), RoundingMode.HALF_DOWN);
        BigDecimal ab = BigDecimal.valueOf(20.0).divide(BigDecimal.valueOf(6), RoundingMode.HALF_DOWN);
        BigDecimal ac = BigDecimal.valueOf(26.0).divide(BigDecimal.valueOf(6), RoundingMode.HALF_DOWN);
        BigDecimal ad = BigDecimal.valueOf(32.0).divide(BigDecimal.valueOf(6), RoundingMode.HALF_DOWN);

        assertAll("Verificando média ponderada",
                () -> assertEquals(aa, mmp.calcula(2, serie)),
                () -> assertEquals(ab, mmp.calcula(3, serie)),
                () -> assertEquals(ac, mmp.calcula(4, serie)),
                () -> assertEquals(ad, mmp.calcula(5, serie))
        );
    }
}