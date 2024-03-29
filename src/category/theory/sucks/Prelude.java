package category.theory.sucks;

import category.theory.sucks.classes.Show;
import category.theory.sucks.collections.List;
import category.theory.sucks.markers.Impure;

import java.util.LinkedList;

public class Prelude {


    public static Morphism<Show, String> show = Show::show;
//    public static Morphism<?, Morphism<Monad<?>, Monad<?>>> ret = x -> {
//        return null; i hate you
//    };

    //    generation methods
    public static Morphism<Integer, Morphism<Integer, List<Integer>>> range() {
        return x -> y -> {
            if (y > x) return Prelude.<Integer>cons().of(y).of(range().of(x).of(y - 1));
            return List.<Integer>ret().of(x);
        };
    }

    // LIST METHODS


    /**
     * cons :: E -> [E] -> [E]
     * although since generics are hard you can also take anything :(
     */
    @Impure
    public static <E> Morphism<E, Morphism<List<? extends E>, List<E>>> cons() {
        return x -> xs -> {
            List<E> newList = new List<>();
            newList.addAll(xs);
            newList.add(x);
            return newList;
        };
    }

    /**
     * concat :: [T] -> [T] -> [T]
     * Appends list 2 to the end of list 1
     * or you can say appends list 1 in front of list 2
     */
    public static <T> Morphism<List<? extends T>, Morphism<List<? extends T>, List<? extends T>>> concat() {
        return x -> xs -> {
            if (xs.size() == 0) return x;
            return (List<? extends T>) Prelude.concat().of(cons().of(head().of(xs)).of(x)).of(tail().of(xs));
        };
    }

    /**
     * length :: [a] -> Integer
     */
    public static Morphism<List<?>, Integer> length = List::size;

    /**
     * head :: [a] -> a
     */
    public static <T> Morphism<List<? extends T>, T> head() {
        return LinkedList::getFirst;
    }

    /**
     * last :: [a] -> a
     */
    public static <T> Morphism<List<? extends T>, T> last() {
        return LinkedList::getLast;
    }

    /**
     * tail :: [a] -> [a]
     */
    @Impure
    public static <T> Morphism<List<? extends T>, List<T>> tail() { // god damnit
        return x -> {
            List<T> xs = (List<T>) x.clone();
            xs.pop(); // semi-pure?
            return xs;
        };
    };
    /**
     * init :: [a] -> [a]
     */
    @Impure
    public static <T> Morphism<List<? extends T>, List<T>> init() { // god damnit
        return x -> {
            List<T> xs = (List<T>) x.clone();
            xs.removeLast(); // semi-pure?
            return xs;
        };
    };

    /**
     * drop :: Integer -> [a] -> [a]
     */
    public static Morphism<Integer, Morphism<List<?>, List<?>>> drop() {
        return a -> xs -> {
            if (a > 0) return drop().of(a - 1).of(tail().of(xs));
            return xs;
        };
    }

    /**
     * reverse :: [a] -> [a]
     */
    public static Morphism<List<?>, List<?>> reverse() {
        return x -> {
            if (length.of(x) == 1) return x;
            return cons().of(head().of(x)).of(reverse().of(tail().of(x)));
        };
    }

    public static <T, U> Morphism<Morphism<T, Morphism<? super U, U>> /* f */ ,Morphism<? super U /* acc */ , Morphism<List<? extends T> /* structure */ , ? extends U>
                    >> foldr() {
        return f -> acc -> xs -> {
            if (length.of(xs) == 0) return acc;
            return (U) f.of(Prelude.<T>last().of(xs)).of(Prelude.<T, U>foldr().of(f).of(acc).of(Prelude.<T>init().of(xs)));
        };
    }

    public static <T, U> Morphism<Morphism<T, U>,
            Morphism<List<? extends T>, List<? extends U>>> map() {
        return (Morphism<T, U> f) -> (xs) -> (List<? extends U>) foldr().of(c -> acc -> cons().of(f.of((T) c)).of((List<U>) acc)).of(new List<>()).of(xs);
    }


}
