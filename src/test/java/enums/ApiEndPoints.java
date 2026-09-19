package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiEndPoints {

    //API
    USERS ("/users"),
    USER_BY_ID ("/users/{id}"),
    PRODUCTS ("/products"),

    //CRM
    USERS_CRM ("/api/v1/User"),
    AUTH_CRM ("/api/v1/App/user");
    private final String path;
}
