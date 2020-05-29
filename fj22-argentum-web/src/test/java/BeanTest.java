import br.com.ferracini.argentum.modelo.Negociacao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>24/05/2020</pre>
 */
public class BeanTest {

    public static String getHorario() {
        //SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return "Atualizado em: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public static void main(String[] args) {
        System.out.println(getHorario());

    }

    @Test
    void argentumBeanTest() {
        ArgentumBean argentumBean = new ArgentumBean();
        List<Negociacao> negociacoes = argentumBean.getNegociacoes();
        Assert.assertTrue(negociacoes.size() > 0);
    }
}
