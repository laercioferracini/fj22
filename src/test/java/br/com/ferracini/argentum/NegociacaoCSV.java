package br.com.ferracini.argentum;

import org.junit.jupiter.params.aggregator.AggregateWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>15/05/2020</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AggregateWith(NegociacaoAggregator.class)
public @interface NegociacaoCSV {
}
