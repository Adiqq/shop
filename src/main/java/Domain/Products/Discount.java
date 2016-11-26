package Domain.Products;

import Domain.Common.Percentage;

import java.util.Date;

public class Discount {
    private DiscountPercentage percentage;
    private Date startDate;
    private Date endDate;

    public Discount(DiscountPercentage percentage, Date startDate, Date endDate) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
