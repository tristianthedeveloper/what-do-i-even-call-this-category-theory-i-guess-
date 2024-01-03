package category.theory.sucks;

import category.theory.sucks.classes.Show;
import category.theory.sucks.collections.List;
import category.theory.sucks.markers.Impure;

import java.util.Objects;

public class Prelude {


    public static Morphism<Show, String> show = Show::show;
//    public static Morphism<?, Morphism<Monad<?>, Monad<?>>> ret = x -> {
//        return null; i hate you
//    };

//    generation methods
//    rangesg
    public static Morphism<Integer, Morphism<Integer, List<Integer>>> range() {
        return x -> y -> {
            if (y > x) return (List<Integer>) cons().of(y).of(range().of(x).of(y-1));
            return (List<Integer>) List.ret().of(x);
        };
    }

    // LIST METHODS


    /**
     * cons :: E -> [E] -> [E]
     * although since generics are hard you can also take anything :(
     */
    @Impure
    public static <E> Morphism<E, Morphism<List<? extends E>, List<? extends E>>> cons() {
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
            return (List<? extends T>) concat().of(cons().of(head.of(xs)).of(x)).of(tail.of(xs));
        };
    }

    /**
     * length :: [a] -> Integer
     */
    public static Morphism<List<?>, Integer> length = List::size;

    /**
     * head :: [a] -> a
     */
    public static Morphism<List<?>, ?> head = List::getFirst;

    /**
     * last :: [a] -> a
     */
    public static Morphism<List<?>, ?> last = List::getLast;

    /**
     * tail :: [a] -> [a]
     */
    @Impure
    public static Morphism<List<?>, List<?>> tail = x -> { // god damnit
        List<?> xs = (List<?>) x.clone();
        xs.pop(); // semi-pure?
        return xs;
    };
    /**
     * tail :: [a] -> [a]
     */
    @Impure
    public static Morphism<List<?>, List<?>> init = x -> { // god damnit
        List<?> xs = (List<?>) x.clone();
        xs.removeLast();// semi-pure?
        return xs;
    };

    /**
     * drop :: Integer -> [a] -> [a]
     */
    public static Morphism<Integer, Morphism<List<?>, List<?>>> drop() {
        return a -> xs -> {
            if (a > 0) return drop().of(a - 1).of(tail.of(xs));
            return xs;
        };
    }

    /**
     * reverse :: [a] -> [a]
     */
    public static Morphism<List<?>, List<?>> reverse() {
        return x -> {
            if (length.of(x) == 1) return x;
            return cons().of(head.of(x)).of(reverse().of(tail.of(x)));
        };
    }


    // foldr non-morphism aka non-good TODO real boy
    public static <T, U> U foldr(Morphism<T, Morphism<U, U>> callback,
                                 U acc, List<T> structure) {
        if (length.of(structure) == 0) return acc;
        return callback.of( (T) head.of(structure)).of(
                foldr (callback, acc, (List<T>) tail.of(structure)));
    }

    public static <T, U> Morphism<
            Morphism<? extends T, Morphism<? extends U, ? extends U>>,
            Morphism<? extends U, Morphism<List<T>, U>>
            > foldr() {
        return (Morphism<T, Morphism<U, U>> f) -> (T acc) -> (List<T> xs) -> {
          if (length.of(xs) == 0)   return acc;
          return f.of((T) head.of(xs)).of(foldr().of(f).of(acc).of((List<T>)tail.of(xs)));
        };

    }



}
