package evaluation.candidate.restapi2.utils;

import evaluation.candidate.restapi2.exceptions.ApiException;
import org.springframework.stereotype.Component;

@Component
public class IdNumberValidator {

    public void validate(Object value) throws ApiException {
        if (value == null) {
            return;
        }

        IdNumber idNumber = new IdNumber((String) value);

        if (!idNumber.validateIdNumber()) {
            throw new ApiException(406, String.format("Validation Error '%s' is not a valid Id Number;", value));
		}
	}

}
