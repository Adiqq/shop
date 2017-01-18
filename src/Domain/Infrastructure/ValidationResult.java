package Domain.Infrastructure;

import java.util.Collection;

/**
 *  Result for model validation
 */
public class ValidationResult {
    private Collection<String> errors;
    public ValidationResult(Collection<String> errors){
        this.errors = errors;
    }

    public Collection<String> getErrors() {
        return errors;
    }

    public boolean isValid(){
        return getErrors().isEmpty();
    }
}
