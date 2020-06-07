package br.com.ferracini.argentum.indicadores;

import br.com.ferracini.argentum.modelo.SerieTemporal;
import org.junit.jupiter.api.DisplayName;
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
@DisplayName(value = "Media movel simples deve")
class MediaMovelSimplesTest {

    @Test
    @DisplayName(value ="Calcular sequencia simples de candles")
    void calcularSequenciaSimplesDeCandles() {
        SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
        MediaMovelSimples mms = new MediaMovelSimples(new IndicadorFechamento());

        BigDecimal aa = BigDecimal.valueOf(10.0).divide(BigDecimal.valueOf(3), RoundingMode.HALF_DOWN);
        BigDecimal ab = BigDecimal.valueOf(13.0).divide(BigDecimal.valueOf(3), RoundingMode.HALF_DOWN);
        BigDecimal ac = BigDecimal.valueOf(11.0).divide(BigDecimal.valueOf(3), RoundingMode.HALF_DOWN);
        int intervalo = 3;
        assertAll("Verificando média simples",
                () -> assertEquals(BigDecimal.valueOf(2.0), mms.calcula(2, serie, intervalo)),
                () -> assertEquals(BigDecimal.valueOf(3.0), mms.calcula(3, serie, intervalo)),
                () -> assertEquals(aa, mms.calcula(4, serie, intervalo)),
                () -> assertEquals(ac, mms.calcula(5, serie, intervalo)),
                () -> assertEquals(BigDecimal.valueOf(4.0), mms.calcula(6, serie, intervalo)),
                () -> assertEquals(ab, mms.calcula(7, serie, intervalo)),
                () -> assertEquals(BigDecimal.valueOf(4.0), mms.calcula(8, serie, intervalo))
        );
    }

    @Test
    @DisplayName(value = "Calcular sequência simples de candles com intervalo parametrizável")
    void calcularSequenciaSimplesDeCandlesComIntervaloParametrizavel() {

        SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
        MediaMovelSimples mms = new MediaMovelSimples(new IndicadorFechamento());
        int intervalo = 5;
        BigDecimal aa = BigDecimal.valueOf(16.0).divide(BigDecimal.valueOf(intervalo), RoundingMode.HALF_DOWN);
        BigDecimal ab = BigDecimal.valueOf(19.0).divide(BigDecimal.valueOf(intervalo), RoundingMode.HALF_DOWN);
        BigDecimal ac = BigDecimal.valueOf(19.0).divide(BigDecimal.valueOf(intervalo), RoundingMode.HALF_DOWN);


        assertAll("Verificando média simples parametrizável",

                () -> assertEquals(aa, mms.calcula(5, serie, intervalo)),

                () -> assertEquals(ab, mms.calcula(6, serie, intervalo)),

                () -> assertEquals(ac, mms.calcula(8, serie, intervalo))
        );
    }

    @Test
    @DisplayName(value = "Calcular media movel simples com indicador de abertura")
    void calcularMediaMovelSimplesComIndicadorAbertura() {

        SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
        MediaMovelSimples mms = new MediaMovelSimples(new IndicadorAbertura());
        int intervalo = 5;
        BigDecimal aa = BigDecimal.valueOf(16.0).divide(BigDecimal.valueOf(intervalo), RoundingMode.HALF_DOWN);
        BigDecimal ab = BigDecimal.valueOf(19.0).divide(BigDecimal.valueOf(intervalo), RoundingMode.HALF_DOWN);
        BigDecimal ac = BigDecimal.valueOf(19.0).divide(BigDecimal.valueOf(intervalo), RoundingMode.HALF_DOWN);


        assertAll("Verificando média simples parametrizável",

                () -> assertEquals(aa, mms.calcula(5, serie, intervalo)),

                () -> assertEquals(ab, mms.calcula(6, serie, intervalo)),

                () -> assertEquals(ac, mms.calcula(8, serie, intervalo))
        );
    }
}