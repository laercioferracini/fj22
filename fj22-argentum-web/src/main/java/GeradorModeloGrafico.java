import br.com.ferracini.argentum.indicadores.Indicador;
import br.com.ferracini.argentum.modelo.SerieTemporal;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>02/06/2020</pre>
 */
public class GeradorModeloGrafico {
    private static final Logger LOGGER = Logger.getLogger(GeradorModeloGrafico.class.getName());
    /**
     * GeradorModeloGrafico nem dá para saber como ele gera o modelo. É um código
     * encapsulado, flexível, pouco acoplado e elegante: usa boas práticas da Orientação a Objetos
     */
    private final SerieTemporal serie;
    private final int inicio;
    private final int fim;
    private final LineChartModel modeloGrafico;
    private final String tituloGrafico;

    public GeradorModeloGrafico(SerieTemporal serie, int inicio, int fim, String tituloGrafico) {
        this.serie = serie;
        this.inicio = inicio;
        this.fim = fim;
        this.modeloGrafico = new LineChartModel();
        this.tituloGrafico = tituloGrafico;
    }

    public void plotaIndicador(Indicador indicador) {

        LineChartSeries chartSeries = new LineChartSeries(indicador.toString());
        for (int i = inicio; i <= fim; i++) {
            BigDecimal valor = indicador.calcula(i, serie,5);
            chartSeries.set(i, valor.setScale(2, RoundingMode.HALF_DOWN));
        }
        this.modeloGrafico.addSeries(chartSeries);
        this.modeloGrafico.setLegendPosition("w");
        this.modeloGrafico.setTitle(tituloGrafico);
    }


    public LineChartModel getModeloGrafico() {
        LOGGER.info("getModeloGrafico()");
        return modeloGrafico;
    }
}
