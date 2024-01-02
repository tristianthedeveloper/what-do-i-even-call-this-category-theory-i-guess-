package category.theory.sucks.classes;

import category.theory.sucks.Morphism;
import category.theory.sucks.markers.Impure;

/**
 *  I can't do instance like in haskell so this has to be impure :(
 */
@Impure
public interface Show {

    @Impure
    String show();


}
