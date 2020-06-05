import br.com.ferracini.argentum.indicadores.IndicadorAbertura;
import br.com.ferracini.argentum.indicadores.IndicadorFechamento;
import br.com.ferracini.argentum.indicadores.MediaMovelPonderada;
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
        List<Candle> candles = new CandlestickFactory().constroiCandles(negociacoes);
        SerieTemporal serie = new SerieTemporal(candles);
        GeradorModeloGrafico grafico = new GeradorModeloGrafico(serie, 0, serie.getUltimaPosicao());
        grafico.plotaIndicador(new MediaMovelSimples());
        grafico.plotaIndicador(new MediaMovelPonderada());
        grafico.plotaIndicador(new IndicadorFechamento());
        grafico.plotaIndicador(new IndicadorAbertura());
        grafico.getModeloGrafico().setAnimate(true);
        this.modeloGrafico = grafico.getModeloGrafico();
    }

    public List<Negociacao> getNegociacoes() {
        return negociacoes;
    }

    public ChartModel getModeloGrafico() {
        return modeloGrafico;
    }
}
