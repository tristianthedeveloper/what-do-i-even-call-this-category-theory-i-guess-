package examples;

import category.theory.sucks.Morphism;
import category.theory.sucks.Prelude;
import category.theory.sucks.classes.Show;
import category.theory.sucks.collections.List;

import java.util.Arrays;
import java.util.stream.IntStream;

import static category.theory.sucks.Prelude.*;
import static category.theory.sucks.collections.List.ret;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        List<Integer> x = new List<>();
        List<Integer> y = new List<>();
        IntStream.range(0, 10).forEach(x::add);
        IntStream.range(10, 20).forEach(y::add);
        System.out.println(concat().of(x).of(y));


        {
            Morphism<Integer, Morphism<Integer, Morphism<Integer, Integer>>> add3 = a -> b -> c -> a + b + c;
            var addFourToTheSumofTwoNumbers = add3.of(4); // add 4 to the sum of 2 numbers
            var addFourToOneNumber = add3.of(1).of(3); // ok this is a really retarded example but it is the example
            System.out.println(add3.of(1).of(2).of(3)); // 1 + 2 + 3
            System.out.println(addFourToTheSumofTwoNumbers.of(1).of(2)); // 4 + (1 + 2)
            System.out.println(addFourToOneNumber.of(4)); // (1 + 3) + 4


        }


        System.out.println(tail().of(x));
        System.out.println(drop().of(2).of(x));
        System.out.println(last().of(x));
        System.out.println(reverse().of(x));

        List<Monkey> monkeys = new List<>();
        System.out.println(range().of(0).of(100));

        System.out.println(foldr().of(a -> b -> (int) a + (int) b).of(0).of(x));

        System.out.println(foldr().of(a -> acc -> cons().of(a).of((List<?>) acc)).of(new List<Integer>()).of(range().of(0).of(10)));

        System.out.println(
                Prelude.foldr()
                        .of(a ->
                                acc ->
                                        cons()
                                                .of(new Monkey("Monkey #" + (int) a, (int) a))
                                                .of((List<Monkey>) acc))
                        .of(new List<Monkey>())
                        .of(reverse()
                                .of(range()
                                        .of(0)
                                        .of(10))));
        System.out.println(map().of(c -> (int) c + 1).of(range().of(0).of(10)));


    }

    /*

    In this exercise, a string is passed to a method and a new string has to be returned with the first character of each word in the string.

For example:

"This Is A Test" ==> "TIAT"

Strings will only contain letters and spaces, with exactly 1 space between words, and no leading/trailing spaces.

my haskell solution was:

solution :: String -> String
solution x = foldr ((:) . head) "" x

my java solution is polymorphic and works for any List[List[T]]
     */
    private static <T> Morphism<List<List<T>>, List<T>> firsts() {
        return (List<List<T>> xs) -> (List<T>) foldr().of(x -> acc -> cons().of(head().of((List<T>) x)).of((List<T>) acc)).of(new List<T>()).of(xs);
    }
    @SuppressWarnings("unchecked") // i know i should fix that but jeezzus
    private static void testFirsts() {
        List<List<Character>> strings = (List<List<Character>>) map().of(xs -> ret((String) xs)).of(new List<>(
                Arrays.asList("Some", "Random", "Ass", "Array", "Of", "Lists", "Of", "Characters", "Also", "Known", "As", "Strings", "But", "Not", "In", "Java")
        ));
        List<List<Integer>> someRanges = (List<List<Integer>>) map().of(
                a -> ret().of(a) // convert the range into a singleton list, aka a list of list of integers
        ).of(range().of(0).of(30));
        System.out.println(someRanges);
        System.out.println(Main.<Integer>firsts().of(someRanges));
        System.out.println(Main.<Character>firsts().of(strings));

    }

    static class Monkey implements Show {
        private final String n;
        private final int a;

        private Monkey(String name, int age) {
            this.n = name;
            this.a = age;
        }

        public static Morphism<String, Morphism<Integer, Monkey>> Monkey = x -> y -> new Monkey(x, y);

        // pattern matching in java is really shit
        public static Morphism<Monkey, String> name = x -> x.n;

        public static Morphism<Monkey, Integer> age = x -> x.a;

        @Override
        public String show() {
            return "Monkey@[name=" + name.of(this) + ",age=" + age.of(this) + "]"; // ignore this for now
        }

        @Override
        public String toString() {
            return show();
        }
    }

}
