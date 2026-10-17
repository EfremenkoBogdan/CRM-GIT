package applications.api.assertions;

import applications.api.assertions.conditions.BodyCondition;
import applications.api.assertions.conditions.StatusCodeCondition;
import org.hamcrest.Matcher;

public class Conditions {

    public static StatusCodeCondition hasStatusCode(int expectedStatusCode) {
        return new StatusCodeCondition(expectedStatusCode);
    }

    public static BodyCondition hasBody(String jsonPath, Matcher<?> matcher) {
        return new BodyCondition(jsonPath, matcher);
    }
}
