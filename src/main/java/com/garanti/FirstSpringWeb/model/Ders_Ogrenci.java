package com.garanti.FirstSpringWeb.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ders_Ogrenci
{
    private Integer ID;

    @NonNull
    private Integer DERS_ID;

    @NonNull
    private Integer OGRENCI_ID;

    @NonNull
    private Integer NOTE;


    @NonNull
    private Integer DEVAMSIZLIK;

    public Ders_Ogrenci(Integer ID, Integer DERS_ID, Integer OGRENCI_ID)
    {
        this.ID = ID;
        this.DERS_ID = DERS_ID;
        this.OGRENCI_ID = OGRENCI_ID;
    }

    public Ders_Ogrenci(Integer DERS_ID, Integer OGRENCI_ID)
    {
        this.DERS_ID = DERS_ID;
        this.OGRENCI_ID = OGRENCI_ID;
    }
}
