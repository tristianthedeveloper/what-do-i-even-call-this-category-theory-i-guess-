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

        System.out.println(tail.of(x));
        System.out.println(drop().of(2).of(x));
        System.out.println(last.of(x));
        System.out.println(reverse().of(x));



    }

}
