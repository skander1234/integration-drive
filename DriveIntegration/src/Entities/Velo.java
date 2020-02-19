/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Belgaroui Ghazi
 */
public class Velo {
    private int id;
    private String type;
    private String adresse;
    private int qte;
    private String photo;
    float prix;

    public Velo(String type, String adresse, int qte, String photo,float prix) {
        
        this.type = type;
        this.adresse = adresse;
        this.qte = qte;
        this.photo = photo;
        this.prix=prix;
    }
    
    public Velo(int id,String type, String adresse, int qte, String photo,float prix) {
        this.id=id;
        this.type = type;
        this.adresse = adresse;
        this.qte = qte;
        this.photo = photo;
        this.prix=prix;
    }

    public Velo() {
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getQte() {
        return qte;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Velo{" + "id=" + id + ", type=" + type + ", adresse=" + adresse + ", qte=" + qte + ", photo=" + photo + ", prix=" + prix + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Velo other = (Velo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return true;
    }

    
   
    
}
