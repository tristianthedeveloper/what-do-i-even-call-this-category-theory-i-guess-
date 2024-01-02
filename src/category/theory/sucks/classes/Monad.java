package category.theory.sucks.classes;

public interface Monad<T> {
    // im really not sure how we can make this work "nicely", we cant have for example like `return []` like in haskell because we cant make this static
//
//    /**
//     * Injects a value into this monadic type
//     * @param a The value to inject
//     * @return monad
//     */
//    Monad<? extends T> ret(T a);
}
