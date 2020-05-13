package br.com.ferracini.argentum.testes;

import br.com.ferracini.argentum.modelo.Candlestick;
import br.com.ferracini.argentum.modelo.CandlestickFactory;
import br.com.ferracini.argentum.modelo.Negociacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
@DisplayName("Candlestick factory deveria")
class CandlestickFactoryTest {

    @Test
    @DisplayName(value = "construir candle para a data corretamente")
    void constroiCandleParaData() {
        LocalDateTime hoje = LocalDateTime.now();

        Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
        Negociacao negociacao2 = new Negociacao(45.5, 100, hoje);
        Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
        Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

        List<Negociacao> negociacaoList = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
        //EFFECTIVE JAVA
        //ITEM 47: Conheça e use as bibliotecas
        double volume = negociacaoList.stream().mapToDouble(e -> e.getPreco() * e.getQuantidade()).sum();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);

        assertAll("Verificando candlestick",
                () -> assertEquals(40.5, candle.getAbertura()),
                () -> assertEquals(42.3, candle.getFechamento()),
                () -> assertEquals(39.8, candle.getMinimo()),
                () -> assertEquals(45.5, candle.getMaximo()),
                () -> assertEquals(volume, candle.getVolume())
        );
        System.out.println(candle.toString());

    }
}