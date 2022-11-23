import java.util.List;

public class DummyLargeObject implements Comparable<DummyLargeObject>{
    private int value;
    private List<String> strings;
    
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
