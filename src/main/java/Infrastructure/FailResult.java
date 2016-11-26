package Infrastructure;

public class FailResult extends Result {
    public FailResult(String description) {
        super(false, description);
    }
}
