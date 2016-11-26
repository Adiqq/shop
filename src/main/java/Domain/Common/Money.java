package Domain.Common;

import java.io.Serializable;
import java.math.BigDecimal;

public class Money implements Serializable {
    private String value;

    public Money(String value) {
        setValue(value);
    }

    public Money(Long value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(Long value) {
        setValue(value.toString());
    }

    public void setValue(String value) {
        this.value = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_EVEN).min(new BigDecimal(0)).toString();
    }

    @Override
    public String toString() {
        return getValue();
    }
}
