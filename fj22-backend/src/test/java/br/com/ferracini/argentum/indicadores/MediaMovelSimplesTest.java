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
 * @since <pre>29/05/2020</pre>
 */
class MediaMovelSimplesTest {

    @Test
    void sequenciaSimplesDeCandles() {

        SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
        MediaMovelSimples mms = new MediaMovelSimples();

        BigDecimal aa = BigDecimal.valueOf(10.0).divide(BigDecimal.valueOf(3), RoundingMode.HALF_DOWN);
        BigDecimal ab = BigDecimal.valueOf(13.0).divide(BigDecimal.valueOf(3), RoundingMode.HALF_DOWN);
        BigDecimal ac = BigDecimal.valueOf(11.0).divide(BigDecimal.valueOf(3), RoundingMode.HALF_DOWN);

        assertAll("Verificando média simples",
                () -> assertEquals(BigDecimal.valueOf(2.0), mms.calcula(2, serie)),
                () -> assertEquals(BigDecimal.valueOf(3.0), mms.calcula(3, serie)),
                () -> assertEquals(aa, mms.calcula(4, serie)),
                () -> assertEquals(ac, mms.calcula(5, serie)),
                () -> assertEquals(BigDecimal.valueOf(4.0), mms.calcula(6, serie)),
                () -> assertEquals(ab, mms.calcula(7, serie)),
                () -> assertEquals(BigDecimal.valueOf(4.0), mms.calcula(8, serie))
        );
    }
}