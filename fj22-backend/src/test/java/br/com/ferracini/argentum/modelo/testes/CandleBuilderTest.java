package br.com.ferracini.argentum.modelo.testes;

import br.com.ferracini.argentum.modelo.CandleBuilder;
import br.com.ferracini.argentum.modelo.Candle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>14/05/2020</pre>
 */
class CandleBuilderTest {

    @Test
    void controiCandleComBuilder() {
        Calendar data = Calendar.getInstance();
        Candle candle = new CandleBuilder()
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
        //Faça um teste geracaoDeCandleDeveTerTodosOsDadosNecessarios que teste isso. O método geraCandle
        //deveria lançar outra exception conhecida da biblioteca Java, a IllegalStateException, quando invocado
        //antes dos seus outros seis métodos já terem sido.
        //O teste deve falhar. Corrija-o criando booleans que indicam se cada método setter foi invocado, ou utilizando alguma outra forma de verificação
        Throwable t = assertThrows(IllegalStateException.class, () -> new CandleBuilder()
                .comAbertura(BigDecimal.valueOf(100))
                .comFechamento(BigDecimal.valueOf(100))
                .comMinimo(BigDecimal.valueOf(90))
                .comMaximo(BigDecimal.valueOf(101))
                .comVolume(BigDecimal.valueOf(10100))
                .comData(null)
                .geraCandle());

        assertEquals("candle não pode ser gerado com dados nulos", t.getMessage(), "Campo abertura");

    }

    @Test
    void geraBuildDeveLancarExceptionQuandoChamadoSemIniciarParametros() {
        Throwable t = assertThrows(IllegalStateException.class, () -> new CandleBuilder()
                .geraCandle());

        assertEquals("candle não pode ser gerado com dados nulos", t.getMessage(), "Geração de candle");

    }
}