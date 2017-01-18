package Domain.Products;

import Domain.Common.Percentage;

import java.time.LocalDate;
import java.util.Date;

/**
 * Discount for products
 */
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
    /**
     * Date setter as string for easier serialization
     * @return
     */
    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }

    /**
     * Date getter as string for easier serialization
     * @return
     */
    public String getStartDate() {
        if(startDate != null) {
            return startDate.toString();
        }
        return null;
    }
    /**
     * Date setter as string for easier serialization
     * @return
     */
    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }
    /**
     * Date getter as string for easier serialization
     * @return
     */
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
