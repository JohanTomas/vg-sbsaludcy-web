package pe.edu.vallegrande.projectsoftware.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicDto {
    private int id;
    private String name;
    private String lastname;
    private String document_type;
    private String document_dni;
    private String day_of_birth;
    private String gender;
    private String email;
    private String address;
    private String phone;
    //private String image;
    private String is_active;
    //private String created_at;
    private String category_id;
    private String category_name;
}

