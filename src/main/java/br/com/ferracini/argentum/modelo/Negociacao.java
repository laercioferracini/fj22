package br.com.ferracini.argentum.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
public final class Negociacao {

    private final BigDecimal preco;
    private final int quantidade;
    private final LocalDateTime data;

    public Negociacao(BigDecimal preco, int quantidade, LocalDateTime data) {
        if (data == null) throw new IllegalArgumentException("data não pode ser nula");
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }


    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public BigDecimal getVolume() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
