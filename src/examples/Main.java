package examples;

import category.theory.sucks.Morphism;
import category.theory.sucks.Prelude;
import category.theory.sucks.collections.List;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.IntStream;

import static category.theory.sucks.Prelude.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> x = new List<>();
        List<Integer> y = new List<>();
        IntStream.range(0, 10).forEach(x::add);
        IntStream.range(10, 20).forEach(y::add);
        System.out.println(concat().of(x).of(y));


        {
            Morphism<Integer, Morphism<Integer, Morphism<Integer, Integer>>> add3 = a -> b -> c -> a + b + c;
            var addFourToTheSumofTwoNumbers= add3.of(4); // add 4 to the sum of 2 numbers
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



    }
   static class Monkey {
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
    }

}
