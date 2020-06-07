import br.com.ferracini.argentum.indicadores.Indicador;
import br.com.ferracini.argentum.indicadores.IndicadorAbertura;
import br.com.ferracini.argentum.indicadores.IndicadorFechamento;
import br.com.ferracini.argentum.indicadores.MediaMovelSimples;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>07/06/2020</pre>
 */
public class IndicadorFactory {

    private static final Logger LOGGER = Logger.getLogger(IndicadorFactory.class.getName());

    private String nomeMedia;
    private String indicadorBase;

    public IndicadorFactory(String nomeMedia, String indicadorBase) {
        this.nomeMedia = nomeMedia;
        this.indicadorBase = indicadorBase;
    }

    public Indicador defineIndicador() {
        LOGGER.info("Definindo Indicador");
        if (indicadorBase == null || nomeMedia == null) return new MediaMovelSimples(new IndicadorFechamento());
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
            throw new RuntimeException("Erro ao definir indicador");
        }
    }
}
