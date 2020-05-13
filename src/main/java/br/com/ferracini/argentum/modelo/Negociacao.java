package br.com.ferracini.argentum.modelo;

import java.time.LocalDateTime;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
public final class Negociacao {

    private final double preco;
    private final int quantidade;
    private final LocalDateTime data;

    public Negociacao(double preco, int quantidade, LocalDateTime data) {
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public double getVolume() {
        return preco * quantidade;
    }
}
