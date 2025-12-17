package pe.edu.vallegrande.projectsoftware.dto;

import lombok.*;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private int id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String is_active;
    private String is_admin;
}
