package fr.ptut2022.simulateur_mcdu;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Donnee {
    private String label;
    private String valeur;
    private String couleur;
}
