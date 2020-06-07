import br.com.ferracini.argentum.indicadores.IndicadorFechamento;
import br.com.ferracini.argentum.indicadores.MediaMovelSimples;
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
import java.util.logging.Logger;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>25/05/2020</pre>
 */
@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {
    private final static Logger LOGGER = Logger.getLogger(ArgentumBean.class.getName());

    private List<Negociacao> negociacoes;
    private ChartModel modeloGrafico;
    private String nomeMedia;
    private String indicadorBase;

    /**
     * A <i>factory</i> é usada pelo {@code GeradorDeSerie} dos testes.
     * A ideia é que criar um objeto {@code SerieTemporal} diretamente é complicado.
     * Então criamos um <i>método de fábrica</i> que encapsula essas complicações
     * e já devolve o objeto prontinho para uso.
     * O padrão Builder é o que estamos usando na classe {@code GeradorModeloGrafico}.
     * Queremos encapsular a criação complicada do modelo e que pode mudar depois com o tempo.
     * Entra aí o <i>objeto construtor</i> da nossa classe Builder: seu único objetivo é
     * descrever os passos para criação do nosso objeto final (o gráfico) e encapsular
     * a complexidade disso.
     */
    public ArgentumBean() {
        this.negociacoes = new ClientWebService().getNegociacoes();
        setGeraGrafico();
    }

    private void setGeraGrafico() {
        LOGGER.info("Plotando: " + nomeMedia + " de " + indicadorBase);
        List<Candle> candles = new CandlestickFactory().constroiCandles(negociacoes);
        SerieTemporal serie = new SerieTemporal(candles);
        GeradorModeloGrafico grafico = new GeradorModeloGrafico(serie, 0, serie.getUltimaPosicao());
        grafico.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento()));
        //grafico.plotaIndicador(new MediaMovelPonderada(new IndicadorFechamento()));
        //grafico.plotaIndicador(new IndicadorFechamento());
        //grafico.plotaIndicador(new IndicadorAbertura());
        grafico.getModeloGrafico().setAnimate(true);
        this.modeloGrafico = grafico.getModeloGrafico();
    }

    public String getIndicadorBase() {
        LOGGER.info("getIndicadorBase()");
        return indicadorBase;
    }

    public void setIndicadorBase(String nomeIndicadorBase) {
        LOGGER.info("setIndicadorBase()");
        this.indicadorBase = nomeIndicadorBase;
    }

    public String getNomeMedia() {
        LOGGER.info("getNomeMedia()");
        return nomeMedia;
    }

    public void setNomeMedia(String nomeMedia) {
        LOGGER.info("nomeMedia()");
        this.nomeMedia = nomeMedia;
    }

    public List<Negociacao> getNegociacoes() {
        LOGGER.info("getNegociacoes()");
        return negociacoes;
    }

    public ChartModel getModeloGrafico() {
        LOGGER.info("getModeloGrafico()");
        return modeloGrafico;
    }
}
