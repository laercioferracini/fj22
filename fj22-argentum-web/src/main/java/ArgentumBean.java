import br.com.ferracini.argentum.modelo.Candle;
import br.com.ferracini.argentum.modelo.CandlestickFactory;
import br.com.ferracini.argentum.modelo.Negociacao;
import br.com.ferracini.argentum.modelo.SerieTemporal;
import br.com.ferracini.argentum.modelo.reader.ClientWebService;
import org.primefaces.model.chart.ChartModel;

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
    private List<Negociacao> negociacoes;
    private String indicadorBase;
    private String media;
    private ChartModel modeloGrafico;

    public ArgentumBean() {
        this.negociacoes = new ClientWebService().getNegociacoes();
        List<Candle> candles = new CandlestickFactory().constroiCandles(negociacoes);
        SerieTemporal serie = new SerieTemporal(candles);
        GeradorModeloGrafico grafico = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());
        grafico.plotaMediaMovelSimples();
        this.modeloGrafico = grafico.getModeloGrafico();
    }

    public List<Negociacao> getNegociacoes() {
        return negociacoes;
    }

    public ChartModel getModeloGrafico() {
        return modeloGrafico;
    }
}
