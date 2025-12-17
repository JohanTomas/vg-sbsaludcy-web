package pe.edu.vallegrande.projectsoftware.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuotesDto {
    private int id;
    private String title;
    private String message;
    private String date_at;
    private String time_at;
    private String symtoms;
    private String is_active;
    /* Parametro Pacient */
    private String pacient_id;
    private String pacient_name;
    private String pacient_lastname;
    /* Parametro Medic */
    private String medic_id;
    private String medic_name;
    private String medic_lastname;
    /* Parametro Admin */
    private String admin_name;
    private String admin_lastname;
    /* Parametro Status */
    private String status_id;
    private String status_name;
    /* Parametro Medic */
    private String payment_id;
    private String payment_name;
}
