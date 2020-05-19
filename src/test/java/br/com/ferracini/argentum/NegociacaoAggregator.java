package br.com.ferracini.argentum;

import br.com.ferracini.argentum.modelo.Negociacao;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>15/05/2020</pre>
 */
public class NegociacaoAggregator implements ArgumentsAggregator {
    @Override
    public Negociacao aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        System.out.println(context.getParameter().getName());
        return new Negociacao(
                accessor.get(0, BigDecimal.class),
                accessor.get(1, Integer.class),
                accessor.get(2, Calendar.class));
    }
}
