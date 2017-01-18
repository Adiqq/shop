package Domain.Common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  Represent money (currency is not taken in account)
 */
public class Money implements Serializable {
    private String value;

    public Money(){}

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
        if(value == null || value.isEmpty()){
            this.value = null;
        } else {

            this.value = new BigDecimal(value).max(BigDecimal.ZERO).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
        }
    }

    public boolean hasValue(){
        return !(getValue() == null || getValue().isEmpty());
    }

    @Override
    public String toString() {
        return getValue();
    }
}
