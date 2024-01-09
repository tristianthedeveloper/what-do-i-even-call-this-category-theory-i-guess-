package category.theory.sucks.collections;

import category.theory.sucks.Morphism;
import category.theory.sucks.classes.Monad;
import category.theory.sucks.markers.Impure;

import java.util.Arrays;
import java.util.LinkedList;

@Impure
public class List<T> extends LinkedList<T> implements Monad<T> {


    @Impure
//    this is the worst one yet
    public static <T> Morphism<T, List<T>> ret() {
        return what -> {
            List<T> l = new List<>();
            l.add(what);
            return l;
        };
    }
}
