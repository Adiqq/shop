package Domain.Products;

import Domain.Common.Percentage;

import java.time.LocalDate;
import java.util.Date;

public class Discount {
    private DiscountPercentage percentage;
    private LocalDate startDate;
    private LocalDate endDate;

    public Discount(){}
    public Discount(DiscountPercentage percentage, LocalDate startDate, LocalDate endDate) {
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Percentage getPercentage() {
        return percentage;
    }

    public void setPercentage(DiscountPercentage percentage) {
        this.percentage = percentage;
    }

    public LocalDate getStartDateAsLocal() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDateAsLocal() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }

    public String getStartDate() {
        if(startDate != null) {
            return startDate.toString();
        }
        return null;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public String getEndDate() {
        if(endDate != null) {
            return endDate.toString();
        }
        return null;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
