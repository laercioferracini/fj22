package br.com.ferracini.argentum.testes;

import br.com.ferracini.argentum.modelo.Candlestick;
import br.com.ferracini.argentum.modelo.CandlestickFactory;
import br.com.ferracini.argentum.modelo.Negociacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
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
    void constroiCandleParaDataCorretamente() {
        LocalDateTime hoje = LocalDateTime.now();

        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
        Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
        Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
        Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);

        List<Negociacao> negociacaoList = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
        //EFFECTIVE JAVA
        //ITEM 47: Conheça e use as bibliotecas
        double volume = 16760.0;// negociacaoList.stream().mapToDouble(e -> Double.parseDouble(e.getPreco().multiply(BigDecimal.valueOf(e.getQuantidade())).toPlainString())).sum();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);

        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(42.3), candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(39.8), candle.getMinimo()),
                () -> assertEquals(BigDecimal.valueOf(45.0), candle.getMaximo()),
                () -> assertEquals(BigDecimal.valueOf(volume), candle.getVolume())
        );
        System.out.println(candle.toString());

    }

    @Test
    @DisplayName(value = "construir candle com uma negociacao corretamente")
    void constroiCandleComUmaNegociacaoCorretamente() {
        LocalDateTime hoje = LocalDateTime.now();

        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);

        List<Negociacao> negociacaoList = Collections.singletonList(negociacao1);

        double volume = negociacaoList.stream().mapToDouble(e -> Double.parseDouble(e.getPreco().multiply(BigDecimal.valueOf(e.getQuantidade())).toPlainString())).sum();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);

        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getMinimo()),
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getMaximo()),
                () -> assertEquals(BigDecimal.valueOf(volume), candle.getVolume())
        );
        System.out.println(candle.toString());

    }

    @Test
    void constroiCandleSemNegociacoesCorretamente() {
        LocalDateTime hoje = LocalDateTime.now();

        List<Negociacao> negociacaoList = Collections.emptyList();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);
        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.ZERO, candle.getAbertura()),
                () -> assertEquals(BigDecimal.ZERO, candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), candle.getMinimo()),
                () -> assertEquals(BigDecimal.ZERO, candle.getMaximo()),
                () -> assertEquals(BigDecimal.ZERO, candle.getVolume())
        );
        System.out.println(candle.toString());
    }

    @Test
    @DisplayName(value = "construir candle negociacoes em ordem crescente corretamente")
    void constroiCandleComNegociacoesEmOrdemCrescenteCorretamente() {
        LocalDateTime hoje = LocalDateTime.now();

        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
        Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);
        Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(45.5), 100, hoje);
        Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(49.8), 100, hoje);
        List<Negociacao> negociacaoList = List.of(negociacao1, negociacao2, negociacao3, negociacao4);

        //EFFECTIVE JAVA
        //ITEM 47: Conheça e use as bibliotecas
        double volume = negociacaoList.stream().mapToDouble(e -> Double.parseDouble(e.getPreco().multiply(BigDecimal.valueOf(e.getQuantidade())).toPlainString())).sum();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);

        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getAbertura(), "Verificando abertura"),
                () -> assertEquals(BigDecimal.valueOf(49.80), candle.getFechamento(), "Verificando fechamento"),
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getMinimo(), "Verificando minimo"),
                () -> assertEquals(BigDecimal.valueOf(49.80), candle.getMaximo(), "Verificando maximo"),
                () -> assertEquals(BigDecimal.valueOf(volume), candle.getVolume(), "Verificando volumme")
        );
        System.out.println(candle.toString());
    }

    @Test
    @DisplayName(value = "construir candle negociacoes em ordem  decrescente corretamente")
    void constroiCandleComNegociacoesEmOrdemDecrescenteCorretamente() {
        LocalDateTime hoje = LocalDateTime.now();

        Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(49.8), 100, hoje);
        Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(45.5), 100, hoje);
        Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);
        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);

        List<Negociacao> negociacaoList = Arrays.asList(negociacao4, negociacao3, negociacao2, negociacao1);
        //EFFECTIVE JAVA
        //ITEM 47: Conheça e use as bibliotecas
        double volume = negociacaoList.stream().mapToDouble(e -> Double.parseDouble(e.getPreco().multiply(BigDecimal.valueOf(e.getQuantidade())).toPlainString())).sum();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);

        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.valueOf(49.80), candle.getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(40.50), candle.getMinimo()),
                () -> assertEquals(BigDecimal.valueOf(49.80), candle.getMaximo()),
                () -> assertEquals(BigDecimal.valueOf(volume), candle.getVolume())
        );
        System.out.println(candle.toString());
    }

    @Test
    public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
        LocalDateTime hoje = LocalDateTime.now();
        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
        var negociacoes = Arrays.asList(negociacao1);
        var fabrica = new CandlestickFactory();
        var candle = fabrica.constroiCandleParaData(hoje, negociacoes);
        assertAll("Verificando candle com valores iguais",
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getMinimo()),
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getMaximo()),
                () -> assertEquals(BigDecimal.valueOf(4050.0), candle.getVolume())
        );
    }
}