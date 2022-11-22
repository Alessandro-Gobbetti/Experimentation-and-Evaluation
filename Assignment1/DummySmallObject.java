public class DummySmallObject {
    private int value;
    public DummySmallObject(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    
    // implement a method that allows me to use > < == operators between two objects
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
