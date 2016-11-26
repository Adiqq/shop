package Infrastructure;

public abstract class Result {
    private boolean isSuccess;
    private String description;

    public Result(boolean isSuccess, String description) {
        this.isSuccess = isSuccess;
        this.description = description;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getDescription() {
        return description;
    }
}
