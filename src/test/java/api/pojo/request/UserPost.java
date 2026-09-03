package api.pojo.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class UserPost {
    private String username;
    private String email;
    private String password;
}
