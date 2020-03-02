package papont.stream;

import org.junit.Test;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SourceOperators {

//1. Источники

    //    empty()
//    Стрим, как и коллекция, может быть пустым, а значит всем последующем операторам нечего будет обрабатывать.
    @Test
    public void test_empty() {
        Stream.empty().forEach(System.out::println);
    }

    //    of(T value)
//    of(T... values)
//    Стрим для одного или нескольких перечисленных элементов.
//    Очень часто вижу, что используют такую конструкцию:
    @Test
    public void test_of() {
        Stream.of(1, 2, 3).forEach(System.out::println);
    }


    //    ofNullable(T t)
//    Появился в Java 9. Возвращает пустой стрим, если в качестве аргумента передан null,
//    в противном случае, возвращает стрим из одного элемента.
    @Test
    public void test_of_nullable() {
        String str = Math.random() > 0.5 ? "I'm feeling lucky" : null;
        Stream.ofNullable(str).forEach(System.out::println);
    }

    //    generate(Supplier s)
//  Возвращает стрим с бесконечной последовательностью элементов,
//    генерируемых функцией Supplier s.
//  Поскольку стрим бесконечный, нужно его ограничивать или
//      осторожно использовать, дабы не попасть в бесконечный цикл.
    @Test
    public void test_generate() {
        Stream.generate(() -> 42).limit(10).forEach(System.out::println);
    }

    //    iterate​(T seed, UnaryOperator f)
//    Возвращает бесконечный стрим с элементами,
//    которые образуются в результате последовательного
//    применения функции f к итерируемому значению.
//    Первым элементом будет seed, затем f(seed), затем f(f(seed)) и так далее.
    @Test
    public void test_iterate() {
        Stream.iterate(1, (x) -> x * 2 + 42).limit(10).forEach(System.out::println);
    }

    //    iterate​(T seed, Predicate hasNext, UnaryOperator f)
//    Появился в Java 9. Всё то же самое, только добавляется ещё один аргумент hasNext:
//    если он возвращает false, то стрим завершается. Это очень похоже на цикл for:
//    for (i = seed; hasNext(i); i = f(i)) {
//    }
    @Test
    public void test_iterate_has_next() {
        Stream.iterate(1, x -> x < 42, x -> x + 6).forEach(System.out::println);
    }

    //    concat(Stream a, Stream b)
//    Объединяет два стрима так, что вначале идут элементы стрима A, а
//    по его окончанию последуют элементы стрима B.
    @Test
    public void test_concat() {
        Stream.concat(
                Stream.of(1, 2, 3),
                Stream.of(4, 5, 6)
        ).forEach(System.out::println);
    }

    //    builder()
//    Создаёт мутабельный объект для добавления элементов
//    в стрим без использования какого-либо контейнера для этого.
    @Test
    public void test_builder() {
        final var builder = Stream.<Integer>builder();
        builder.add(0).add(1);
        builder
                .add(4)
                .build()
                .forEach(System.out::println);

    }

//    IntStream.range​(int startInclusive, int endExclusive)
//            LongStream.range​(long startInclusive, long endExclusive)
//    Создаёт стрим из числового промежутка [start..end), то есть от start (включительно) по end.
    @Test
    public void test_range () {
        IntStream.range(0, 10).forEach(System.out::println);


        LongStream.range(-10L, -5L).forEach(System.out::println);
    }


//IntStream.rangeClosed​(int startInclusive, int endInclusive)
//LongStream.range​Closed(long startInclusive, long endInclusive)
//    Создаёт стрим из числового промежутка [start..end],
//    то есть от start (включительно) по end (включительно).
@Test
public void test_range_closed () {
    IntStream.rangeClosed(0, 10).forEach(System.out::println);


    LongStream.rangeClosed(-10L, -5L).forEach(System.out::println);
}
}
