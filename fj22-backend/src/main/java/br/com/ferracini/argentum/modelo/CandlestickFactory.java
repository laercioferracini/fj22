package br.com.ferracini.argentum.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
public class CandlestickFactory {

    public Candle constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {
        BigDecimal maximo;
        BigDecimal minimo;
        BigDecimal volume;
        maximo = negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(0).getPreco();
        minimo = negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(0).getPreco();
        volume = BigDecimal.ZERO;

        for (Negociacao negociacao : negociacoes) {
            volume = volume.add(negociacao.getVolume());
            BigDecimal preco = negociacao.getPreco();
            if (preco.compareTo(maximo) > 0) {
                maximo = preco;
            } else if (preco.compareTo(minimo) < 0) {
                minimo = preco;
            }
        }
        BigDecimal abertura = negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(0).getPreco();
        BigDecimal fechamento =
                negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(negociacoes.size() - 1).getPreco();

        return new Candle(abertura, fechamento, minimo, maximo, volume, data);
    }

    public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {

        todasNegociacoes = todasNegociacoes.stream().sorted(Comparator.comparing(Negociacao::getData))
                .collect(Collectors.toList());

        List<Candle> candles = new ArrayList<>();

        List<Negociacao> negociacoesDoDia = new ArrayList<>();
        Calendar dataAtual = todasNegociacoes.get(0).getData();

        for (Negociacao negociacao : todasNegociacoes) {
            if (negociacao.getData().before(dataAtual)) throw new IllegalStateException("negociações em ordem errada");
            if (!negociacao.isMesmoDia(dataAtual)) {
                criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
                negociacoesDoDia = new ArrayList<>();
                dataAtual = negociacao.getData();
            }
            negociacoesDoDia.add(negociacao);
        }

        //adiciona último candle
        criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);

        return candles;
    }

    private void criaEGuardaCandle(List<Candle> candles, List<Negociacao> negociacoesDoDia, Calendar dataAtual) {
        // quebrar essa refatoração
        // em pedaços pequenos e rodar os testes a cada passo
        Candle candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
        candles.add(candleDoDia);
    }
}
