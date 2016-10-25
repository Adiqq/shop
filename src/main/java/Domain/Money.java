package Domain;

import java.math.BigDecimal;

/**
 * Created by Adiq on 16.10.2016.
 */
public class Money {
    private BigDecimal value;

    public Money() {
    }

    public Money(String value) {
        setValue(value);
    }

    public String getValue() {
        return value == null ? "0" : value.toString();
    }

    public void setValue(String value) {
        this.value = new BigDecimal(value);
    }
}
