import java.util.List;

/**
 * A dummy large object. This object is usless, but it is large enough to be used to 
 * test the performance of a sorting algorithm with large objects.
 */
public class DummyLargeObject implements Comparable<DummyLargeObject>{
    private int value;
    private List<String> strings;
    
    /**
     * Creates a new dummy large object.
     * @param value The value of the object.
     * @param strings The list of strings of the object.
     */
    public DummyLargeObject(int value, List<String> strings) {
        this.value = value;
        this.strings = strings;
    }
    
    @Override
    public int compareTo(DummyLargeObject o) {
        if (this.value > o.value) {
            return 1;
        } else if (this.value < o.value) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
