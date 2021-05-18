package com.example.opiniaturistului;

import android.graphics.Bitmap;

public class ModelClass {
    private String descriere;
    private String nume;
    private Bitmap image;

    public String getRecomandare() {
        return recomandare;
    }

    public void setRecomandare(String recomandare) {
        this.recomandare = recomandare;
    }

    private String recomandare;
    public ModelClass(String descriere, String nume, Bitmap image,String recomandare) {
        this.descriere = descriere;
        this.nume = nume;
        this.image = image;
        this.recomandare=recomandare;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
