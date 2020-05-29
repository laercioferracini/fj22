package br.com.ferracini.argentum.modelo.reader;

import br.com.ferracini.argentum.modelo.Negociacao;
import br.com.ferracini.argentum.modelo.gerador.GeradorAletatorioDeXML;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>19/05/2020</pre>
 */
public class LeitorXML {

    @SuppressWarnings("unchecked")
    public List<Negociacao> carrega(InputStream inputStream) {
        XStream stream = new XStream(new DomDriver());
        stream.alias("negociacao", Negociacao.class);
        Object o = stream.fromXML(inputStream);

        return o != null ? (List<Negociacao>) o : Collections.emptyList();
    }
}
