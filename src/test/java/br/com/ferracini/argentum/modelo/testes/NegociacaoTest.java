package br.com.ferracini.argentum.modelo.testes;

import br.com.ferracini.argentum.modelo.Negociacao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>15/05/2020</pre>
 */
class NegociacaoTest {

    @Test
    public void dataDaNegociacaoEhImutavel() {
        //Effective Java
        //Item 39: Faça cópias defensivas quando necessário.
        // se criar um negocio no dia 15...
        Calendar data = Calendar.getInstance(); //.of(2020, 5, 15, 0, 0, 0);
        data.set(Calendar.DAY_OF_MONTH, 15);
        Negociacao n = new Negociacao(BigDecimal.valueOf(10), 5, data);
        // ainda que eu tente mudar a data para 20...
        n.getData().set(Calendar.DAY_OF_MONTH, 20);
        // ele continua no dia 15.
        assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
    }

    @Test()
    public void naoCriaNegociacaoComDataNula() {
        //Effective Java
        //Item 60: Favoreça o uso das exceções padrões!
        Throwable error = assertThrows(IllegalArgumentException.class, () ->
                new Negociacao(BigDecimal.valueOf(10), 5, null));
        assertEquals("Data não pode ser nula", error.getMessage());
    }

    @Test
    void mesmoMilisegundoEhDoMesmoDia() {
        Calendar agora = Calendar.getInstance();
        Calendar mesmoDia = (Calendar) agora.clone();

        Negociacao negociacao = new Negociacao(BigDecimal.valueOf(23.1), 100, agora);
        assertTrue(negociacao.isMesmoDia(mesmoDia));

    }

    @Test
    void comHorariosDiferentesEhNoMesmoDia() {
        Calendar manha = new GregorianCalendar(2020, Calendar.MAY, 21, 8, 30);
        Calendar tarde = new GregorianCalendar(2020, Calendar.MAY, 21, 13, 30);

        Negociacao negociacao = new Negociacao(BigDecimal.valueOf(23.1), 100, manha);
        assertTrue(negociacao.isMesmoDia(tarde));
    }
}