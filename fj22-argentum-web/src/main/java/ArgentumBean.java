import br.com.ferracini.argentum.modelo.Negociacao;
import br.com.ferracini.argentum.modelo.reader.ClientWebService;

import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>25/05/2020</pre>
 */
@ManagedBean
public class ArgentumBean {
    public List<Negociacao> getNegociacoes() {
        return new ClientWebService().getNegociacoes();
    }
}
