package br.com.ferracini.argentum.modelo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>14/05/2020</pre>
 */
class CandleBuilderTest {

    @Test
    void controiCandleComBuilder() {
        LocalDateTime data = LocalDateTime.now().minusDays(20);
        Candlestick candle = new CandleBuilder()
                .comAbertura(BigDecimal.valueOf(40.5))
                .comFechamento(BigDecimal.valueOf(42.3))
                .comMinimo(BigDecimal.valueOf(39.8))
                .comMaximo(BigDecimal.valueOf(45.5))
                .comVolume(BigDecimal.valueOf(145234.20))
                .comData(data)
                .geraCandle();

        assertAll("Verificando candle",
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(42.30), candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(39.80), candle.getMinimo()),
                () -> assertEquals(BigDecimal.valueOf(45.50), candle.getMaximo()),
                () -> assertEquals(BigDecimal.valueOf(145234.20), candle.getVolume()),
                () -> assertEquals(data, candle.getData())
        );
        System.out.println(candle.toString());

    }
}