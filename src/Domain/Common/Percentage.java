package Domain.Common;

/**
 *  Value type representing percentage
 */
public abstract class Percentage {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value) + "%";
    }
}
