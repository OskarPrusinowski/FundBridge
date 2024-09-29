package pl.hackyeah.hackyeah24_backend.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyRegisterRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String nip;

    @NotBlank
    private String nazwa;

    private String opis;

    private String sektor;

    private String wielkosc;

    private String logo_id;

    private String strona_internetowa;

    private Boolean otwarta_na_zlecenia;

    private Integer minimalny_budzet;

    private Integer maksymalny_budzet;
}