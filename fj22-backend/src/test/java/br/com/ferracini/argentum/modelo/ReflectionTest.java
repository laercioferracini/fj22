package br.com.ferracini.argentum.modelo;

import br.com.ferracini.argentum.indicadores.MediaMovelSimples;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>07/06/2020</pre>
 */
public class ReflectionTest {

    @Test
    void verificaListAtributos(){
        Class<Negociacao> classe = Negociacao.class;
        for (Field field : classe.getDeclaredFields()) {
            System.out.println(field.getName());
        }
        Class<Negociacao> classe1 = Negociacao.class;
        for (Method metodo : classe1.getDeclaredMethods()) {
            System.out.println(metodo.getName());
        }
        Class<MediaMovelSimples> classe2 = MediaMovelSimples.class;
        Constructor<?>[] construtores = classe2.getConstructors();
        for (Constructor<?> constructor : construtores) {
            System.out.println(Arrays.toString(constructor.getParameterTypes()));
        }
    }
}
