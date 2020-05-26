import javax.faces.bean.ManagedBean;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>24/05/2020</pre>
 */
@ManagedBean
public class OlaMundoBean {
    //    String data = LocalDate.now().format(DateTimeFormatter
//            .ofLocalizedDateTime(FormatStyle.MEDIUM)
//            .withLocale(new Locale("pt", "br")));
    private String nome;
    private String mensagem;


    public String getNome() {
        System.out.println("getNome");
        return nome;
    }
    public void setNome(String nome) {
        System.out.println("setNome");
        this.nome = nome;
    }
    public String getMensagem() {
        System.out.println("getMensagem");
        return mensagem;
    }

    public void nomeFoiDigitado() {
        System.out.println("\nChamou o botão");
    }
    public String getHorario() {
        System.out.println("getHorario");
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return "Atualizado em " + sdf.format(new Date());
    }
}
