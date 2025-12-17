package pe.edu.vallegrande.projectsoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrescriptionDto {
    private int id;

    private String reservation_id;
    private String title;
    private String pacient_name;
    private String pacient_lastname;
    private String medic_name;
    private String medic_lastname;

    private String sickness;
    private String medicaments;
    private String price;
    private String created_at;
    private String is_active;
}
