import br.com.ferracini.argentum.indicadores.Indicador;
import br.com.ferracini.argentum.indicadores.IndicadorAbertura;
import br.com.ferracini.argentum.indicadores.IndicadorFechamento;
import br.com.ferracini.argentum.indicadores.MediaMovelSimples;
import br.com.ferracini.argentum.modelo.Candle;
import br.com.ferracini.argentum.modelo.CandlestickFactory;
import br.com.ferracini.argentum.modelo.Negociacao;
import br.com.ferracini.argentum.modelo.SerieTemporal;
import br.com.ferracini.argentum.modelo.reader.ClientWebService;
import org.primefaces.model.chart.ChartModel;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        geraGrafico();
    }

    public void geraGrafico() {
        LOGGER.info("Plotando: " + nomeMedia + " de " + indicadorBase);
        List<Candle> candles = new CandlestickFactory().constroiCandles(negociacoes);
        SerieTemporal serie = new SerieTemporal(candles);
        GeradorModeloGrafico grafico = new GeradorModeloGrafico(serie, 0, serie.getUltimaPosicao());
        grafico.plotaIndicador(defineIndicador());
        //grafico.plotaIndicador(new MediaMovelPonderada(new IndicadorFechamento()));
        //grafico.plotaIndicador(new IndicadorFechamento());
        //grafico.plotaIndicador(new IndicadorAbertura());
        grafico.getModeloGrafico().setAnimate(true);
        this.modeloGrafico = grafico.getModeloGrafico();
    }

    private Indicador defineIndicador() {
        LOGGER.info("Definindo Indicador");
        if (indicadorBase == null || nomeMedia == null) return new MediaMovelSimples(new IndicadorAbertura());
        String pacote = "br.com.ferracini.argentum.indicadores.";
        try {
            LOGGER.info(pacote + indicadorBase);
            Class<?> classeIndicadorBase = Class.forName(pacote + indicadorBase);

            Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();
            LOGGER.info(pacote + nomeMedia);
            Class<?> classeMedia = Class.forName(pacote + nomeMedia);
            Constructor<?> constructorMedia = classeMedia.getConstructor(Indicador.class);
            Indicador indicador = (Indicador) constructorMedia.newInstance(indicadorBase);
            return indicador;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            LOGGER.severe("Erro ao definir indicador: " + e.getMessage());
            throw new RuntimeException();
        }
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
