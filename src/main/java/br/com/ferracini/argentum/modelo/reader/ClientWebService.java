package br.com.ferracini.argentum.modelo.reader;

import br.com.ferracini.argentum.modelo.Negociacao;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>21/05/2020</pre>
 */
public class ClientWebService {

    private static final String URL_WEBSERVICE =
            "http://argentumws.caelum.com.br/negociacoes";

    public List<Negociacao> getNegociacoes() {
        HttpURLConnection connection = null;
        InputStream inputStream;
        try {
            URL url = new URL(URL_WEBSERVICE);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            return new LeitorXML().carrega(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            Objects.requireNonNull(connection).disconnect();
        }
    }
}
