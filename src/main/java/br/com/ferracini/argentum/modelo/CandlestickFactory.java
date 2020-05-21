package br.com.ferracini.argentum.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
public class CandlestickFactory {
    public Candlestick constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {
        BigDecimal maximo;
        BigDecimal minimo;
        BigDecimal volume;
        maximo = negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(0).getPreco();
        minimo = negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(0).getPreco();
        volume = BigDecimal.ZERO;

        for (Negociacao negociacao : negociacoes) {
            volume = volume.add(negociacao.getVolume());
            if (negociacao.getPreco().compareTo(maximo) > 0) {
                maximo = negociacao.getPreco();
            } else if (negociacao.getPreco().compareTo(minimo) < 0) {
                minimo = negociacao.getPreco();
            }
        }
        BigDecimal abertura = negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(0).getPreco();
        BigDecimal fechamento =
                negociacoes.isEmpty() ? BigDecimal.ZERO : negociacoes.get(negociacoes.size() - 1).getPreco();

        return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
    }

    public List<Candlestick> constroiCandles(List<Negociacao> todasNegociacoes) {
        List<Candlestick> candles = new ArrayList<>();

        List<Negociacao> negociacoesDoDia = new ArrayList<>();
        Calendar dataAtual = todasNegociacoes.get(0).getData();

        for (Negociacao negociacao : todasNegociacoes) {
            if (negociacao.getData().before(dataAtual)) throw new IllegalStateException("negociações em ordem errada");
            if (!negociacao.isMesmoDia(dataAtual)) {
                Candlestick candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
                candles.add(candleDoDia);
                negociacoesDoDia = new ArrayList<>();
                dataAtual = negociacao.getData();
            }
            negociacoesDoDia.add(negociacao);
        }

        //adiciona último candle
        Candlestick candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
        candles.add(candleDoDia);

        return candles;
    }
}
