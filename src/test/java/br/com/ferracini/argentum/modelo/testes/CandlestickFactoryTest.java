package br.com.ferracini.argentum.modelo.testes;

import br.com.ferracini.argentum.modelo.Candlestick;
import br.com.ferracini.argentum.modelo.CandlestickFactory;
import br.com.ferracini.argentum.modelo.Negociacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
        Calendar hoje = Calendar.getInstance();

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
        Calendar hoje = Calendar.getInstance();

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
    void semNegociacoesGeraCandleComZerosCorretamente() {
        Calendar hoje = Calendar.getInstance();

        List<Negociacao> negociacaoList = Collections.emptyList();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);
        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.ZERO, candle.getAbertura(), "Verificando abertura"),
                () -> assertEquals(BigDecimal.ZERO, candle.getFechamento(), "Verificando fechamento"),
                () -> assertEquals(BigDecimal.ZERO, candle.getMinimo(), "Verificando minimo"),
                () -> assertEquals(BigDecimal.ZERO, candle.getMaximo()),
                () -> assertEquals(BigDecimal.ZERO, candle.getVolume())
        );
        System.out.println(candle.toString());
    }

    @Test
    public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
        Calendar hoje = Calendar.getInstance();
        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
        var negociacoes = Collections.singletonList(negociacao1);
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

    @Test
    void precoMaximoNaoPodeSerMenorQueMinimo() {
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> new Candlestick(BigDecimal.valueOf(10), BigDecimal.valueOf(20),
                        BigDecimal.valueOf(20), BigDecimal.valueOf(10), BigDecimal.valueOf(10000), Calendar.getInstance()));
        assertEquals("Valor máximo não pode ser menor que o valor mínimo", throwable.getMessage());
    }

    @Test
    void precoNaoPodeSerNulo() {
        //5) (opcional) Um Candlestick pode ter data nula? Pode ter algum valor negativo?
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> new Candlestick(BigDecimal.valueOf(10), BigDecimal.valueOf(20),
                        BigDecimal.valueOf(20), BigDecimal.valueOf(30), BigDecimal.valueOf(10000), null));
        assertEquals("Data não pode ser nula", throwable.getMessage(), "Campo data");
    }

    @Test
    @DisplayName(value = "construir candle negociacoes em ordem crescente corretamente")
    void negociacoesEmOrdemCrescenteDeValorCorretamente() {
        //6) (opcional) Crie mais dois testes na CandlestickFactoryTest: o negociacoesEmOrdemCrescenteDeValor
        //e negociacoesEmOrdemDecrescenteDeValor, que devem fazer o que o próprio nome diz
        Calendar hoje = Calendar.getInstance();

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
    void negociacoesEmOrdemDecrescenteDeValorCorretamente() {
        //6) (opcional) Crie mais dois testes na CandlestickFactoryTest: o negociacoesEmOrdemCrescenteDeValor
        //e negociacoesEmOrdemDecrescenteDeValor, que devem fazer o que o próprio nome diz
        Calendar hoje = Calendar.getInstance();

        Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(49.8), 100, hoje);
        Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(45.5), 100, hoje);
        Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);
        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);

        List<Negociacao> negociacaoList = Arrays.asList(negociacao4, negociacao3, negociacao2, negociacao1);

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
    void quandoAberturaIgualFechamentoEhAlta() {
        //Se você fez os opcionais do primeiro exercício do capítulo anterior (criação do projeto e dos modelos)
        //você tem os métodos isAlta e isBaixa na classe Candlestick. Contudo, temos um comportamento não
        //especificado nesses métodos: e quando o preço de abertura for igual ao de fechamento?
        //Perguntando para nosso cliente, ele nos informou que, nesse caso, o candle deve ser considerado de alta.
        //Crie o teste quandoAberturaIgualFechamentoEhAlta dentro de CandlestickTest, verifique se isso está
        //ocorrendo. Se o teste falhar, faça mudanças no seu código para que a barra volte a ficar verde!
        Calendar hoje = Calendar.getInstance();

        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
        Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
        Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
        Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);

        List<Negociacao> negociacaoList = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);

        double volume = negociacaoList.stream().mapToDouble(e -> Double.parseDouble(e.getPreco().multiply(BigDecimal.valueOf(e.getQuantidade())).toPlainString())).sum();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);

        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(39.8), candle.getMinimo()),
                () -> assertEquals(BigDecimal.valueOf(45.0), candle.getMaximo()),
                () -> assertEquals(BigDecimal.valueOf(volume), candle.getVolume()),
                () -> assertTrue(candle.isAlta(), "Verificando se candle é de alta")
        );
        System.out.println(candle.toString());
    }

    @Test
    void quandoAberturaMaiorQueFechamentoEhAlta() {
        Calendar hoje = Calendar.getInstance();

        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
        Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
        Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
        Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(42.5), 100, hoje);

        List<Negociacao> negociacaoList = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);

        double volume = negociacaoList.stream().mapToDouble(e -> Double.parseDouble(e.getPreco().multiply(BigDecimal.valueOf(e.getQuantidade())).toPlainString())).sum();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);

        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(42.5), candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(39.8), candle.getMinimo()),
                () -> assertEquals(BigDecimal.valueOf(45.0), candle.getMaximo()),
                () -> assertEquals(BigDecimal.valueOf(volume), candle.getVolume()),
                () -> assertTrue(candle.isAlta(), "Verificando se candle é de alta")
        );
        System.out.println(candle.toString());
    }

    @Test
    void quandoAberturaMenorQueFechamentoEhBaixa() {
        Calendar hoje = Calendar.getInstance();

        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
        Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
        Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
        Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(40.4), 100, hoje);

        List<Negociacao> negociacaoList = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);

        double volume = negociacaoList.stream().mapToDouble(e -> Double.parseDouble(e.getPreco().multiply(BigDecimal.valueOf(e.getQuantidade())).toPlainString())).sum();

        CandlestickFactory factory = new CandlestickFactory();
        Candlestick candle = factory.constroiCandleParaData(hoje, negociacaoList);

        assertAll("Verificando candlestick",
                () -> assertEquals(BigDecimal.valueOf(40.5), candle.getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(40.4), candle.getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(39.8), candle.getMinimo()),
                () -> assertEquals(BigDecimal.valueOf(45.0), candle.getMaximo()),
                () -> assertEquals(BigDecimal.valueOf(volume), candle.getVolume()),
                () -> assertTrue(candle.isBaixa(), "Verificando se candle é de baixa")
        );
        System.out.println(candle.toString());
    }

    @Test
    void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
        Calendar hoje = Calendar.getInstance();

        Negociacao negociacao1 = new Negociacao(BigDecimal.valueOf(40.5), 100, hoje);
        Negociacao negociacao2 = new Negociacao(BigDecimal.valueOf(45.0), 100, hoje);
        Negociacao negociacao3 = new Negociacao(BigDecimal.valueOf(39.8), 100, hoje);
        Negociacao negociacao4 = new Negociacao(BigDecimal.valueOf(42.3), 100, hoje);

        Calendar amanha = (Calendar) hoje.clone();
        amanha.add(Calendar.DAY_OF_MONTH, 1);

        Negociacao negociacao5 = new Negociacao(BigDecimal.valueOf(48.8), 100, amanha);
        Negociacao negociacao6 = new Negociacao(BigDecimal.valueOf(49.3), 100, amanha);

        Calendar depoisDeAmanha = (Calendar) amanha.clone();
        depoisDeAmanha.add(Calendar.DAY_OF_MONTH, 1);

        Negociacao negociacao7 = new Negociacao(BigDecimal.valueOf(51.8), 100, depoisDeAmanha);
        Negociacao negociacao8 = new Negociacao(BigDecimal.valueOf(52.3), 100, depoisDeAmanha);

        List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2,
                negociacao3, negociacao4, negociacao5, negociacao6, negociacao7, negociacao8);

        CandlestickFactory factory = new CandlestickFactory();

        List<Candlestick> candlesticks = factory.constroiCandles(negociacoes);

        assertAll("Verificando lista de candles",
                () -> assertEquals(3, candlesticks.size()),
                () -> assertEquals(BigDecimal.valueOf(40.5), candlesticks.get(0).getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(42.3), candlesticks.get(0).getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(48.8), candlesticks.get(1).getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(49.3), candlesticks.get(1).getFechamento()),
                () -> assertEquals(BigDecimal.valueOf(51.8), candlesticks.get(2).getAbertura()),
                () -> assertEquals(BigDecimal.valueOf(52.3), candlesticks.get(2).getFechamento())
        );

    }
}