import br.com.ferracini.argentum.modelo.Negociacao;
import br.com.ferracini.argentum.modelo.reader.ClientWebService;

import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>25/05/2020</pre>
 */
public class ArgentumBean {
    public List<Negociacao> getNegociacoes() {
        return new ClientWebService().getNegociacoes();
    }
}
