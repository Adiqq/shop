package Domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Adiq on 16.10.2016.
 */
public class Money implements Serializable {
    private String value;

    public Money() {
        this(0l);
    }

    public Money(String value) {
        setValue(value);
    }

    public Money(Long value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
    }

    public void setValue(Long value) {
        setValue(value.toString());
    }

    @Override
    public String toString() {
        return getValue();
    }
}
