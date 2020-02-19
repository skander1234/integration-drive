/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author Meriem
 */
public class Offre {

    private int id_offre;
    private Timestamp date_d;
    private Timestamp date_f;
    private String type;
    private String nom;
    private float reduction_offre;
    private String code_promo;
    private float reduction_promo;
    private float prix_offre;
    
   

    public Offre() {
    }

    public Offre(Timestamp date_d, Timestamp date_f, String type, String nom, float reduction_offre, String code_promo, float reduction_promo, float prix_offre) {
        this.date_d = date_d;
        this.date_f = date_f;
        this.type = type;
        this.nom = nom;
        this.reduction_offre = reduction_offre;
        this.code_promo = code_promo;
        this.reduction_promo = reduction_promo;
        this.prix_offre = prix_offre;
    }
    

    public Offre(Timestamp date_d, Timestamp date_f, String type, String nom, String code_promo, float reduction_promo, float prix_offre) {
        this.date_d = date_d;
        this.date_f = date_f;
        this.type = type;
        this.nom = nom;
        this.code_promo = code_promo;
        this.reduction_promo = reduction_promo;
        this.prix_offre = prix_offre;
    }

    public Offre(Timestamp date_d, Timestamp date_f, String type, String nom, float reduction_offre, float prix_offre) {
        this.date_d = date_d;
        this.date_f = date_f;
        this.type = type;
        this.nom = nom;
        this.reduction_offre = reduction_offre;
        this.prix_offre = prix_offre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

  

    public float getPrix_offre() {
        return prix_offre;
    }

    public void setPrix_offre(float prix_offre) {
        this.prix_offre = prix_offre;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public Timestamp getDate_d() {
        return date_d;
    }

    public void setDate_d(Timestamp date_d) {
        this.date_d = date_d;
    }

    public Timestamp getDate_f() {
        return date_f;
    }

    public void setDate_f(Timestamp date_f) {
        this.date_f = date_f;
    }

 
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getReduction_offre() {
        return reduction_offre;
    }

    public void setReduction_offre(float reduction_offre) {
        this.reduction_offre = reduction_offre;
    }

    public String getCode_promo() {
        return code_promo;
    }

    public void setCode_promo(String code_promo) {
        this.code_promo = code_promo;
    }

    public float getReduction_promo() {
        return reduction_promo;
    }

    public void setReduction_promo(float reduction_promo) {
        this.reduction_promo = reduction_promo;
    }

    @Override
    public String toString() {
        String chaine="{ id : "+ id_offre +" ,date debut : "+ date_d+ " ,date Fin : "+ date_f+ " ,type : "+type+" ,nom : "+nom;
    if (code_promo!= null){
            chaine +=", code_promo=" + code_promo + ", reduction_promo=" + reduction_promo;
        }else{
            chaine +=", reduction_offre=" + reduction_offre +  ", prix_offre=" + prix_offre + '}';
        }
        return chaine;  }

}
