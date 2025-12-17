package pe.edu.vallegrande.projectsoftware.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {
    private int id;
    private String name;
    private String is_active;
}