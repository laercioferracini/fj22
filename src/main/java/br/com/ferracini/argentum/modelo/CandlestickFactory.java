package br.com.ferracini.argentum.modelo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
public class CandlestickFactory {
    public Candlestick constroiCandleParaData(LocalDateTime data, List<Negociacao> negociacoes) {
        double maximo = negociacoes.get(0).getPreco();
        double minimo = negociacoes.get(0).getPreco();
        double volume = 0;
        for (Negociacao negociacao : negociacoes) {
            volume += negociacao.getVolume();
            if (negociacao.getPreco() > maximo) {
                maximo = negociacao.getPreco();
            } else if (negociacao.getPreco() < minimo) {
                minimo = negociacao.getPreco();
            }
        }
        double abertura = negociacoes.get(0).getPreco();
        double fechamento = negociacoes.get(negociacoes.size() - 1).getPreco();

        return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
    }
}
