package domain.validators;

import domain.Problem;
import java.util.HashMap;
import java.util.Optional;

public class ProblemValidator implements Validator<Problem> {
    private String errorMessage;

    @Override
    public void validate(Problem entity) throws ValidatorException {
        this.errorMessage = "";
        HashMap<String, String> userMessages = new HashMap<String, String>() {
            {
                put("problemNo", "Problem's number is not right.\n");
                put("problemStatement", "Problem's statement is not right.\n");
                put("problemPoints", "Problem's number of points is not right.\n");
            }
        };
    }
}
