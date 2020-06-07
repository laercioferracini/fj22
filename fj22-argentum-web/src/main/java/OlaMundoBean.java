import javax.faces.bean.ManagedBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>24/05/2020</pre>
 */
@ManagedBean
public class OlaMundoBean {
    private static final Logger LOGGER = Logger.getLogger(OlaMundoBean.class.getName());
    //    String data = LocalDate.now().format(DateTimeFormatter
//            .ofLocalizedDateTime(FormatStyle.MEDIUM)
//            .withLocale(new Locale("pt", "br")));
    private String nome;
    private String mensagem;


    public String getNome() {
        LOGGER.info("getNome");
        return nome;
    }

    public void setNome(String nome) {
        LOGGER.info("setNome");
        this.nome = nome;
    }

    public String getMensagem() {
        LOGGER.info("getMensagem");
        return mensagem;
    }

    public void nomeFoiDigitado() {
        LOGGER.info("\nChamou o botão");
    }

    public String getHorario() {
        LOGGER.info("getHorario");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return "Atualizado em " + sdf.format(new Date());
    }
}
