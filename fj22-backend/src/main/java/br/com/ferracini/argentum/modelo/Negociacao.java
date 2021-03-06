package br.com.ferracini.argentum.modelo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>13/05/2020</pre>
 */
@XStreamAlias("negociacao")
public final class Negociacao implements Serializable {

    private final BigDecimal preco;
    private final int quantidade;
    private final Calendar data;

    public Negociacao(BigDecimal preco, Integer quantidade, Calendar data) {
        if (preco == null || quantidade == null)
            throw new IllegalArgumentException("Campo preço/quantidade não pode ser nula");
        if (data == null) throw new IllegalArgumentException("Data não pode ser nula");
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

    public Calendar getData() {
        return (Calendar) data.clone();
    }

    public BigDecimal getVolume() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    public boolean isMesmoDia(Calendar outraData) {
        return
                this.data.get(Calendar.DAY_OF_MONTH) == outraData.get(Calendar.DAY_OF_MONTH) &&
                        this.data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH) &&
                        this.data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return "Negociacao{" +
                "preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }
}
