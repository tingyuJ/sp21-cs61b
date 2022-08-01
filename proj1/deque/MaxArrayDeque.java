package deque;

import java.util.Comparator;
//import java.util.Iterator;


//https://github.com/exuanbo/cs61b-sp21/tree/main/proj1/deque
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 1; i < size(); i++) {
            if (c.compare(get(i), get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }

    public T max() {
        return max(comparator);
    }

    //The autograder said this methods should be removed or made private
    // but can't make it private so...
//    @Override
//    public boolean equals(Object o) {
//        if (o == null) {
//            return false;
//        }
//        if (o == this) {
//            return true;
//        }
//        if (!(o instanceof MaxArrayDeque)) {
//            return false;
//        }
//        if (((MaxArrayDeque<?>) o).max() != max()) {
//            return false;
//        }
//        return super.equals(o);
//    }
}
