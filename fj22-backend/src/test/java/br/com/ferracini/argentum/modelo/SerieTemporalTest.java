package br.com.ferracini.argentum.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>30/05/2020</pre>
 */
class SerieTemporalTest {

    @Test
    void serieTempoNaoDeveReceberListaNula() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new SerieTemporal(null));
        assertEquals("Lista não pode ser nula", e.getMessage());
    }


}