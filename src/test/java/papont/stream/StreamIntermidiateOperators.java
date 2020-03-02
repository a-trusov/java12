package papont.stream;

import org.junit.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamIntermidiateOperators {

    //    filter​(Predicate predicate)
//    Фильтрует стрим, принимая только те элементы, которые удовлетворяют заданному условию.
    @Test
    public void test_filter() {
        Stream.of(1, 2, 3)
                .filter(x -> x < 3)
                .forEach(System.out::println);
    }

    //    map​(Function mapper)
//    Применяет функцию к каждому элементу и затем возвращает стрим, в котором элементами
//    будут результаты функции. map можно применять для изменения типа элементов.
//
//            Stream.mapToDouble​(ToDoubleFunction mapper)
//            Stream.mapToInt​(ToIntFunction mapper)
//            Stream.mapToLong​(ToLongFunction mapper)
//            IntStream.mapToObj(IntFunction mapper)
//            IntStream.mapToLong(IntToLongFunction mapper)
//            IntStream.mapToDouble(IntToDoubleFunction mapper)
//
//    Специальные операторы для преобразования объектного стрима в примитивный,
//    примитивного в объектный, либо примитивного стрима одного типа в примитивный стрим другого.
    @Test
    public void test_map() {
        Stream.of(1, 2, 3)
                .map(x -> x + 10)
                .forEach(System.out::println);

        Stream.of("3", "4", "5")
                .map(Integer::parseInt)
                .map(x -> x + 10)
                .forEach(System.out::println);
    }

//    flatMap​(Function<T, Stream<R>> mapper)
//    Один из самых интересных операторов. Работает как map, но с одним отличием —
//    можно преобразовать один элемент в ноль, один или множество других.
//
//    flatMapToDouble​(Function mapper)
//    flatMapToInt​(Function mapper)
//    flatMapToLong​(Function mapper)
//
//    Как и в случае с map, служат для преобразования в примитивный стрим.
//
//    Для того, чтобы один элемент преобразовать в ноль элементов,
//    нужно вернуть null, либо пустой стрим.
//    Чтобы преобразовать в один элемент, нужно вернуть стрим из одного элемента,
//    например, через Stream.of(x). Для возвращения нескольких элементов,
//    можно любыми способами создать стрим с этими элементами.

    @Test
    public void test_flat_map() {
        Stream.of(2, 3, 0, 1, 3)
                .flatMapToInt(x -> IntStream.range(0, x))
                .forEach(System.out::println);
    }

    //    limit​(long maxSize)
//    Ограничивает стрим maxSize элементами.
    @Test
    public void test_limit() {
        Stream.of(2, 3, 0, 1, 3)
                .limit(2)
                .forEach(System.out::println);
    }

    //    skip​(long n)
//    Пропускает n элементов стрима.
    @Test
    public void test_skip() {
        Stream.of(2, 3, 0, 1, 3)
                .skip(2)
                .forEach(System.out::println);
    }

    //    sorted​()
//    sorted​(Comparator comparator)
//    Сортирует элементы стрима. Причём работает этот оператор очень хитро:
//    если стрим уже помечен как отсортированный, то сортировка проводиться не будет,
//    иначе соберёт все элементы, отсортирует их и вернёт новый стрим, помеченный как
//    отсортированный.
    @Test
    public void test_sorted() {
        IntStream.range(0, 10000000)
                .sorted()
                .limit(3)
                .forEach(System.out::println);

        Stream.of(120, 410, 85, 32, 314, 12)
                .sorted()
                .forEach(System.out::println);
    }

    //    distinct​()
//    Убирает повторяющиеся элементы и возвращаем стрим с уникальными элементами.
//    Как и в случае с sorted, смотрит, состоит ли уже стрим из уникальных элементов и
//    если это не так, отбирает уникальные и помечает стрим как содержащий уникальные элементы.
    @Test
    public void test_distinct() {
//        Stream.of(1, 1, 2, 3, 3, 5)
//                .distinct()
//                .forEach(System.out::println);

        IntStream.concat(
                IntStream.range(2, 5),
                IntStream.range(0, 4))
                .distinct()
                .forEach(System.out::println);
    }

    //    peek​(Consumer action)
//    Выполняет действие над каждым элементом стрима и при этом возвращает
//    стрим с элементами исходного стрима. Служит для того, чтобы передать элемент куда-нибудь,
//    не разрывая при этом цепочку операторов (вы же помните, что forEach — терминальный оператор
//    и после него стрим завершается?), либо для отладки.
    @Test
    public void test_peek() {
        Stream.of(0, 3, 0, 0, 5)
                .peek(x -> System.out.format("before distinct: %d%n", x))
                .distinct()
                .peek(x -> System.out.format("after distinct: %d%n", x))
                .map(x -> x * x)
                .forEach(x -> System.out.format("after map: %d%n", x));

    }

    //    takeWhile​(Predicate predicate)
//    Появился в Java 9. Возвращает элементы до тех пор, пока они удовлетворяют условию,
//    то есть функция-предикат возвращает true. Это как limit, только не с числом, а с условием.
    @Test
    public void test_take_while() {
        Stream.of(1, 2, 3, 4, 2, 5)
                .takeWhile(x -> x < 3)
                .forEach(System.out::println);

    }

    //    dropWhile​(Predicate predicate)
//    Появился в Java 9. Пропускает элементы до тех пор, пока они удовлетворяют условию,
//    затем возвращает оставшуюся часть стрима.
//    Если предикат вернул для первого элемента false, то ни единого элемента
//    не будет пропущено. Оператор подобен skip, только работает по условию.
    @Test
    public void test_drop_while() {
        Stream.of(1, 2, 3, 4, 2, 5)
                .dropWhile(x -> x >= 3)
                .forEach(System.out::println);
// 1, 2, 3, 4, 2, 5

        Stream.of(1, 2, 3, 4, 2, 5)
                .dropWhile(x -> x < 3)
                .forEach(System.out::println);
// 3, 4, 2, 5
    }

    //    boxed()
//    Преобразует примитивный стрим в объектный.
    @Test
    public void test_boxed() {
        DoubleStream.of(0.1, Math.PI)
                .boxed()
                .map(Object::getClass)
                .forEach(System.out::println);
//        class java.lang.Double
//        class java.lang.Double
    }
}
