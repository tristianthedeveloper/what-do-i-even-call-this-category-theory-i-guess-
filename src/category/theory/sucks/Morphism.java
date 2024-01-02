package category.theory.sucks;


/**

 this is a really bad idea*
 @param <T> input type
 @param <U> output type
 */
public interface Morphism<T, U> {

    U of(T x);

    default <V> Morphism<V, U> comp(Morphism<? super V, ? extends T> g) {
        return (V v) -> of(g.of(v));
    }


    static <T> Morphism<T, T> identity() { return x -> x; }

}