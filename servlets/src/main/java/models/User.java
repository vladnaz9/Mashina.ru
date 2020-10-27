package models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String dop_inf;
}
