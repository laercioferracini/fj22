import br.com.ferracini.argentum.indicadores.MediaMovelPonderada;
import br.com.ferracini.argentum.indicadores.MediaMovelSimples;
import br.com.ferracini.argentum.modelo.SerieTemporal;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import java.math.BigDecimal;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>02/06/2020</pre>
 */
public class GeradorModeloGrafico {
    /**
     * GeradorModeloGrafico nem dá para saber como ele gera o modelo. É um código
     * encapsulado, flexível, pouco acoplado e elegante: usa boas práticas da Orientação a Objetos
     */
    private final SerieTemporal serie;
    private final int inicio;
    private final int fim;
    private final LineChartModel modeloGrafico;

    public GeradorModeloGrafico(SerieTemporal serie, int inicio, int fim) {
        this.serie = serie;
        this.inicio = inicio;
        this.fim = fim;
        this.modeloGrafico = new LineChartModel();
    }

    public void plotaMediaMovelSimples() {
        MediaMovelSimples indicador = new MediaMovelSimples();
        LineChartSeries chartSerie = new LineChartSeries("MMS Fechamento");
        for (int i = inicio; i <= fim; i++) {
            BigDecimal valor = indicador.calcula(i, serie);
            chartSerie.set(i, valor);
        }
        this.modeloGrafico.addSeries(chartSerie);
        this.modeloGrafico.setLegendPosition("w");
        this.modeloGrafico.setTitle("Indicadores");
    }

    public void plotaMediaMovelPonderada() {
        MediaMovelPonderada indicador = new MediaMovelPonderada();
        LineChartSeries chartSeries = new LineChartSeries("MMP Fechamento");
        for (int i = inicio; i <= fim; i++) {
            BigDecimal valor = indicador.calcula(i, serie);
            chartSeries.set(i, valor);
        }
        this.modeloGrafico.addSeries(chartSeries);
        this.modeloGrafico.setLegendPosition("w");
        this.modeloGrafico.setTitle("Indicadores");
    }

    public LineChartModel getModeloGrafico() {
        return modeloGrafico;
    }
}
