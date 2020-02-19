/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author HP
 */
public class Taxi {
        private int id_taxi;
        private Chauffeur chauffeur;
        private String photo;
        private String num_chassis;

public Taxi()
{
}

    public Taxi(Chauffeur chauffeur, String photo,String num_chassis) {
        this.chauffeur = chauffeur;
        this.photo = photo;
        this.num_chassis=num_chassis;
    }

    public String getNum_chassis() {
        return num_chassis;
    }
    
    
    public int getId_taxi() {
        return id_taxi;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId_taxi(int id_taxi) {
        this.id_taxi = id_taxi;
    }

    public void setChauffeur( Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNum_chassis(String num_chassis) {
        this.num_chassis = num_chassis;
    }

    @Override
    public String toString() {
        return "Taxi{" + "id_taxi=" + id_taxi + ", " + chauffeur.toString() + ", photo=" + photo + ", num_chassis=" + num_chassis + '}';
    }
    
    
  

}
