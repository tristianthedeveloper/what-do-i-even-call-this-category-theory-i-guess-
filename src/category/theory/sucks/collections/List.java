package category.theory.sucks.collections;

import category.theory.sucks.Morphism;
import category.theory.sucks.classes.Monad;
import category.theory.sucks.markers.Impure;

import java.util.Arrays;
import java.util.LinkedList;

@Impure
public class List<T> extends LinkedList<T> implements Monad<T> {


    /**
     *  Injects a value into this monad
     */
    public static <T> List<? extends T> ret(T a) { // das all it does
        List<T> l = new List<>();
        l.add(a);
        return l;
    }
}
