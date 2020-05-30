import br.com.ferracini.argentum.modelo.Negociacao;
import br.com.ferracini.argentum.modelo.reader.ClientWebService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>25/05/2020</pre>
 */
@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {
    public List<Negociacao> getNegociacoes() {
        return new ClientWebService().getNegociacoes();
    }
}
