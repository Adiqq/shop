package UI.Model;
import Domain.Common.Percentage;
import Domain.Products.Discount;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.Date;

public class DiscountViewModel {
    public LocalDate getBeginDate() {
        return beginDate.get();
    }

    public ObjectProperty<LocalDate> beginDateProperty() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public Percentage getPercentage() {
        return percentage.get();
    }

    public ObjectProperty<Percentage> percentageProperty() {
        return percentage;
    }

    private final ObjectProperty<LocalDate> beginDate;
    private final ObjectProperty<LocalDate> endDate;
    private final ObjectProperty<Percentage> percentage;
    private Discount discount;

    public DiscountViewModel(Discount discount) {
        this.discount = discount;
        this.beginDate = new SimpleObjectProperty<>();
        this.endDate = new SimpleObjectProperty<>();
        this.percentage = new SimpleObjectProperty<>();
        if(discount.getStartDate() != null){
            this.beginDate.setValue(discount.getStartDateAsLocal());
        }
        if(discount.getEndDate() != null){
            this.endDate.setValue(discount.getEndDateAsLocal());
        }
        if(discount.getPercentage() != null){
            this.percentage.setValue(discount.getPercentage());
        }
    }

    public Discount getDiscount() {
        return discount;
    }
}
