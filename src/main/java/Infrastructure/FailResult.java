package Infrastructure;

/**
 * Created by Adiq on 16.10.2016.
 */
public class FailResult extends Result {
    public FailResult(String description) {
        super(false, description);
    }
}
