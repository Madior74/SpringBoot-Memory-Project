package com.example.demo.DTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevoirDTO {
    private Double note;
    private String dateAttribution; // au format ISO 8601
    private Long etudiantId;
    private Long professeurId;
    private Long moduleId;
}
