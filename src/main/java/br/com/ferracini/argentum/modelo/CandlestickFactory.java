package br.com.ferracini.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
public class CandlestickFactory {
    public Candlestick constroiCandleParaData(LocalDateTime data, List<Negociacao> negociacoes) {
        BigDecimal maximo = negociacoes.get(0).getPreco();
        BigDecimal minimo = negociacoes.get(0).getPreco();
        BigDecimal volume = BigDecimal.ZERO;
        for (Negociacao negociacao : negociacoes) {
            volume = volume.add(negociacao.getVolume());
            if (negociacao.getPreco().compareTo(maximo) > 0) {
                maximo = negociacao.getPreco();
            } else if (negociacao.getPreco().compareTo(minimo) < 0) {
                minimo = negociacao.getPreco();
            }
        }
        BigDecimal abertura = negociacoes.get(0).getPreco();
        BigDecimal fechamento = negociacoes.get(negociacoes.size() - 1).getPreco();

        return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
    }
}
