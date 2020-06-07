import br.com.ferracini.argentum.indicadores.Indicador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>07/06/2020</pre>
 */
@DisplayName(value = "Indicador Factory deve")
class IndicadorFactoryTest {

    @Test
    @DisplayName(value = "definir indicador MediaMovelSimples quando nomeMedia for nulo")
    void defineIndicadorPadraoQuandoNomeMediaForNull() {
        IndicadorFactory factory = new IndicadorFactory(null, "IndicadorFechamento");
        Indicador indicador = factory.defineIndicador();
        assertEquals("MMS de Indicador Fechamento", indicador.toString());
    }

    @Test
    @DisplayName(value = "definir indicador MediaMovelSimples quando indicadorBase for nulo")
    void defineIndicadorPadraoQuandoIndicadorBaseForNull() {
        IndicadorFactory factory = new IndicadorFactory("MediaMovelSimples", null);
        Indicador indicador = factory.defineIndicador();
        assertEquals("MMS de Indicador Fechamento", indicador.toString());
    }
    @Test
    void definirIndicadorMediaMovelSimplesEIndicadorAbertura(){
        IndicadorFactory factory = new IndicadorFactory("MediaMovelSimples", "IndicadorAbertura");
        Indicador indicador = factory.defineIndicador();
        assertEquals("MMS de Indicador Abertura", indicador.toString());
    }
    @Test
    void definirIndicadorMediaMovelPonderadaEIndicadorAbertura(){
        IndicadorFactory factory = new IndicadorFactory("MediaMovelPonderada", "IndicadorAbertura");
        Indicador indicador = factory.defineIndicador();
        assertEquals("MMP de Indicador Abertura", indicador.toString());
    }
    @Test
    void definirIndicadorMediaMovelPonderadaEIndicadorFechamento(){
        IndicadorFactory factory = new IndicadorFactory("MediaMovelPonderada", "IndicadorFechamento");
        Indicador indicador = factory.defineIndicador();
        assertEquals("MMP de Indicador Fechamento", indicador.toString());
    }

    @Test
    void exibirMensagemDeExcecaoQuandoIndicadorBaseInvalido(){
        IndicadorFactory factory = new IndicadorFactory("MediaMovelPonderada", "IndicadorBugado");
        Throwable e = assertThrows(RuntimeException.class, factory::defineIndicador);
        assertEquals("Erro ao definir indicador", e.getMessage());
    }

    @Test
    void exibirMensagemDeExcecaoQuandoNomeMediaInvalido(){
        IndicadorFactory factory = new IndicadorFactory("MediaMovelExcepcional", "IndicadorFechamento");
        Throwable e = assertThrows(RuntimeException.class, factory::defineIndicador);
        assertEquals("Erro ao definir indicador", e.getMessage());
    }
}