/**
 * A dummy small object. This object is usless, but it is small enough to be used to 
 * test the performance of a sorting algorithm with small objects.
 */ 
public class DummySmallObject implements Comparable<DummySmallObject> {

    private int value;
    
    /**
     * Creates a new dummy small object.
     * @param value The value of the object.
     */
    public DummySmallObject(int value) {
        this.value = value;
    }
    
    @Override
    public int compareTo(DummySmallObject o) {
        if (this.value > o.value) {
            return 1;
        } else if (this.value < o.value) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
