package br.com.ferracini.argentum.modelo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        LocalDateTime data = LocalDateTime.of(2020, 5, 15, 0, 0, 0);
        Negociacao n = new Negociacao(BigDecimal.valueOf(10), 5, data);
        // ainda que eu tente mudar a data para 20...
        n.getData().plusDays(2);
        // ele continua no dia 15.
        assertEquals(15, n.getData().getDayOfMonth());
    }

    @Test()
    public void naoCriaNegociacaoComDataNula() {
        //Effective Java
        //Item 60: Favoreça o uso das exceções padrões!
        Throwable error = assertThrows(IllegalArgumentException.class, () ->
                new Negociacao(BigDecimal.valueOf(10), 5, null));
        assertEquals("data não pode ser nula", error.getMessage());

        ;
    }
}