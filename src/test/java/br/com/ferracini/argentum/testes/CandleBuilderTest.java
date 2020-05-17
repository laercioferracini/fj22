package br.com.ferracini.argentum.testes;

import br.com.ferracini.argentum.modelo.CandleBuilder;
import br.com.ferracini.argentum.modelo.Candlestick;
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

    @Test
    void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
        //TODO 1) Crie um teste para o CandleBuilder. Ele possui um grande erro: se só chamarmos alguns dos métodos,
        //e não todos, ele construirá um Candle inválido, com data nula, ou algum número zerado.
        //Faça um teste geracaoDeCandleDeveTerTodosOsDadosNecessarios que tente isso. O método geraCandle
        //deveria lançar outra exception conhecida da biblioteca Java, a IllegalStateException, quando invocado
        //antes dos seus outros seis métodos já terem sido.
        //O teste deve falhar. Corrija-o criando booleans que indicam se cada método setter foi invocado, ou utilizando alguma outra forma de vericação

    }
}