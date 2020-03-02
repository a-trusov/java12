package papont.stream;

import org.junit.Test;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class TerminalOperators {


//    void forEach​(Consumer action)
//    Выполняет указанное действие для каждого элемента стрима.
@Test
public void test_for_each() {
    Stream.of(120, 410, 85, 32, 314, 12)
            .forEach(x -> System.out.format("%s, ", x));
}
}
