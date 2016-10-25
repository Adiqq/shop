package Domain;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by Adiq on 16.10.2016.
 */
public class Money {
    private Currency currency;
    private BigDecimal value;

    public Money(int value) {
        this(new BigDecimal(value));
    }

    public Money(BigDecimal value) {
        this(Currency.getInstance("PLN"), value);
    }


    public Money(Currency currency, BigDecimal value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }
}
