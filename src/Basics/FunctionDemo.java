package Basics;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 函数式接口应用：
 * <p>
 * Consumer<T>: 接受一个参数，并对其进行操作。不返回操作结果。
 * Supplier<T>: 不接受任何参数，返回一个结果。
 * Function<T, R>: 接受一个参数 T，返回一个结果 R。
 * Predicate<T>: 接受一个参数 T，返回一个布尔值结果。
 * <p>
 * BiConsumer<T, U>: 接受两个参数 T 和 U，并对它们进行操作。不返回操作结果。
 * BiFunction<T, U, R>: 接受两个参数 T 和 U，返回一个结果 R。
 * BiPredicate<T, U>: 接受两个参数 T 和 U，返回一个布尔值结果。
 * <p>
 * UnaryOperator<T>: 接受一个参数 T，返回一个结果 T。
 * BinaryOperator<T>: 接受两个参数 T，返回一个结果 T。
 */
public class FunctionDemo {
    public static void main(String[] args) {
        //Consumer<T>：消费者接口，用于接收一个参数并执行操作，没有返回值。
        Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());
        printUpperCase.accept("hello"); // 输出: HELLO
        List<Integer> numbers23 = Arrays.asList(1, 2, 3, 4, 5);
        numbers23.forEach(System.out::println); // 相当于 numbers.forEach(n -> System.out.println(n));
        //使用Consumer进行遍历：
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
        fruits.stream().forEach(fruit -> System.out.println(fruit));

        //Supplier<T>：供应商接口，用于生成一个值。
        Supplier<LocalDate> currentDate = LocalDate::now;
        LocalDate today = currentDate.get(); // 获取当前日期
        //使用Supplier生成流：
        Stream<Integer> numberStream = Stream.generate(() -> new Random().nextInt(100));

        //Function<T, R>：函数接口，用于接收一个参数并返回结果。
        Function<Integer, Double> squareRoot = n -> Math.sqrt(n);
        double result = squareRoot.apply(9); // 计算数字的平方根
        //使用Function进行映射： 使用map()方法和Function函数式接口，将字符串列表转换为它们的长度。
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> nameLengths = names.stream()
                .map(s -> s.length())
                .collect(Collectors.toList());

        //多测试
        String stringA = names.stream().collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();

        //Predicate<T>：断言接口，用于进行条件判断。
        Predicate<String> isLong = s -> s.length() > 5;
        boolean result1 = isLong.test("example"); // 判断字符串长度是否大于5
        //使用Predicate进行过滤： 使用filter()方法和Predicate函数式接口，筛选出列表中的偶数。
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());


        //BiConsumer
        //处理两个列表的元素：
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(4, 5, 6);
        BiConsumer<Integer, Integer> printSum = (num1, num2) -> System.out.println(num1 + num2);
        IntStream.range(0, Math.min(numbers1.size(), numbers2.size()))
                .forEach(i -> printSum.accept(numbers1.get(i), numbers2.get(i)));


        //UnaryOperator<T>：一元操作符接口，输入和输出类型相同。
        UnaryOperator<Integer> negate = n -> -n;
        int result2 = negate.apply(5); // 对数字取负值
        //BinaryOperator<T>：二元操作符接口，输入和输出类型相同。
        BinaryOperator<Integer> multiply = (x, y) -> x * y;
        int result3 = multiply.apply(3, 4); // 计算两个数的乘积



        Integer data=5;
        List<Integer> collect = Stream.generate(() -> data).limit(3).collect(Collectors.toList());
        System.out.println(collect);
    }
}
