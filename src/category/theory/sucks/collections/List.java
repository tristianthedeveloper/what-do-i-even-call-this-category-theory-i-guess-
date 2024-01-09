package category.theory.sucks.collections;

import category.theory.sucks.Morphism;
import category.theory.sucks.classes.Monad;
import category.theory.sucks.markers.Impure;

import java.util.Collection;
import java.util.LinkedList;

@Impure
public class List<T> extends LinkedList<T> implements Monad<T> {


    public List() {
        super();
    }
    public List(Collection<? extends T> c) {
        super();
        addAll(c);
    }

    @Impure
//    this is the worst one yet
    public static <T> Morphism<T, List<T>> ret() {
        return what -> {
            List<T> l = new List<>();
            l.add(what);
            return l;
        };
    }

    @Impure
//    this is the worst worst one yet
    public static List<Character> ret(String what) {
        List<Character> l = new List<>();
        for (char c : what.toCharArray()) {
            l.add(c);
        }
        return l;
    }

    ;
}
