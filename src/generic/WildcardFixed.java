package generic;

import java.util.List;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-28 11:09
 **/

public class WildcardFixed {

    void foo(List<?> i) {
        fooHelper(i);
    }

    void fooF(List<?> i) {
        //i.set(0, i.get(0));
    }

    // Helper method created so that the wildcard can be captured
    // through type inference.
    private <T> void fooHelper(List<T> l) {
        l.set(0, l.get(0));
    }

}