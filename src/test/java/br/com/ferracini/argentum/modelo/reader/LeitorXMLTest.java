package br.com.ferracini.argentum.modelo.reader;

import br.com.ferracini.argentum.modelo.Negociacao;
import com.thoughtworks.xstream.converters.ConversionException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>19/05/2020</pre>
 */
class LeitorXMLTest {

    @Test
    void carregaXmlComUmaNegociacaoEmListaUnitaria() {

        String xml = "<list> +\n" +
                "<negociacao> +\n" +
                "<preco>43.5</preco> +\n" +
                "<quantidade>1000</quantidade> +\n" +
                "<data> +\n" +
                "<time>1322233344455</time> +\n" +
                "</data> +\n" +
                "</negociacao> +\n" +
                "</list>";


        LeitorXML leitor = new LeitorXML();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        List<Negociacao> negociacoes = leitor.carrega(inputStream);
        assertAll("Validando lista negociações",
                () -> assertEquals(1, negociacoes.size()),
                () -> assertEquals(BigDecimal.valueOf(43.5), negociacoes.get(0).getPreco()),
                () -> assertEquals(1000, negociacoes.get(0).getQuantidade())
        );
    }

    @Test
    void carregaXmlComNenhumaNegociacao() {

        List<Negociacao> negociacoes = new LeitorXML().carrega(new ByteArrayInputStream("<list></list>".getBytes()));

        assertAll("Validando lista negociações",
                () -> assertEquals(0, negociacoes.size(), "tamanho da lista")
        );
    }

    @Test
    void carregaXmlComNegociacaoFaltandoPreco() {

        String xml = "<list> +\n" +
                "<negociacao> +\n" +
                "<preco> </preco> +\n" +
                "<quantidade> </quantidade> +\n" +
                "<data> +\n" +
                "<time>1322233344455</time> +\n" +
                "</data> +\n" +
                "</negociacao> +\n" +
                "</list>";


        LeitorXML leitor = new LeitorXML();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        Throwable exception = assertThrows(ConversionException.class, () -> leitor.carrega(inputStream));

        //assertEquals("", exception.getMessage());
    }

    @Test
    void carregaXmlComListNegociacao() {

        String xml = "<list> +\n" +
                "<negociacao> +\n" +
                "<preco>43.5</preco> +\n" +
                "<quantidade>1000</quantidade> +\n" +
                "<data> +\n" +
                "<time>1322233344455</time> +\n" +
                "</data> +\n" +
                "</negociacao> +\n" +
                "<negociacao> +\n" +
                "<preco>44.5</preco> +\n" +
                "<quantidade>2000</quantidade> +\n" +
                "<data> +\n" +
                "<time>1322233344455</time> +\n" +
                "</data> +\n" +
                "</negociacao> +\n" +
                "<negociacao> +\n" +
                "<preco>48.5</preco> +\n" +
                "<quantidade>1000</quantidade> +\n" +
                "<data> +\n" +
                "<time>1322233344455</time> +\n" +
                "</data> +\n" +
                "</negociacao> +\n" +
                "<negociacao> +\n" +
                "<preco>42.5</preco> +\n" +
                "<quantidade>1000</quantidade> +\n" +
                "<data> +\n" +
                "<time>1322233344455</time> +\n" +
                "</data> +\n" +
                "</negociacao> +\n" +
                "</list>";


        LeitorXML leitor = new LeitorXML();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        List<Negociacao> negociacoes = leitor.carrega(inputStream);
        double volume = negociacoes.stream().mapToDouble(e -> Double.parseDouble(e.getPreco().multiply(BigDecimal.valueOf(e.getQuantidade())).toPlainString())).sum();
        assertAll("Validando lista negociações",
                () -> assertEquals(4, negociacoes.size()),
                () -> assertEquals(BigDecimal.valueOf(44.5), negociacoes.get(1).getPreco()),
                () -> assertEquals(2000, negociacoes.get(1).getQuantidade())
        );
    }
}