/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Armand
 */
public class Reservation {
    private int id_reservation;
    private Client client;
    private Chauffeur chauffeur;
    private String depart;
    private String arrive;
    private Timestamp heure;
    private double prix;
    private String type_reservation;
    private int code_liv;
    private int nbr_place;

    public Reservation() {
    }

    public Reservation(Client client, Chauffeur chauffeur, String depart, String arrive, Timestamp heure, double prix, String type_reservation, int code_liv) {
        this.client = client;
        this.chauffeur = chauffeur;
        this.depart = depart;
        this.arrive = arrive;
        this.heure = heure;
        this.prix = prix;
        this.type_reservation = "livraison";
        this.code_liv = code_liv;
    }

   public Reservation(Client client, Chauffeur chauffeur, String depart, String arrive, Timestamp heure, double prix, int nbrplace) {
        this.client = client;
        this.chauffeur = chauffeur;
        this.depart = depart;
        this.arrive = arrive;
        this.heure = heure;
        this.prix = prix;
        this.type_reservation = "covoiturage";
        this.nbr_place = nbrplace;
    }
   
    public Reservation(Client client, Chauffeur chauffeur, String depart, String arrive, Timestamp heure, double prix) {
        this.client = client;
        this.chauffeur = chauffeur;
        this.depart = depart;
        this.arrive = arrive;
        this.heure = heure;
        this.prix = prix;
        this.type_reservation = "personnel";
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public Timestamp getHeure() {
        return heure;
    }

    public void setHeure(Timestamp heure) {
        this.heure = heure;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType_reservation() {
        return type_reservation;
    }

    public void setType_reservation(String type_reservation) {
        this.type_reservation = type_reservation;
    }

    public int getCode_liv() {
        return code_liv;
    }

    public void setCode_liv(int code_liv) {
        this.code_liv = code_liv;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    @Override
    public String toString() {
        String mp= "Reservation{" + "id_reservation=" + id_reservation + ", " + client.toString() + ", " + chauffeur.toString() + ", depart=" + depart + ", arrive=" + arrive + ", heure=" + heure + ", prix=" + prix + ", type_reservation=" + type_reservation;
        if(code_liv != 0){
             mp += ", code_liv=" + code_liv + ", nbr_place=" + nbr_place + '}';
        }
           if(type_reservation.equals("covoiturage")){
             mp += ", nbr_place=" + nbr_place + '}';
        }
           
           return mp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id_reservation;
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
        final Reservation other = (Reservation) obj;
        if (this.client.getId_user() != other.client.getId_user()) {
            return false;
        }
        if (!Objects.equals(this.depart, other.depart)) {
            return false;
        }
        if (!Objects.equals(this.arrive, other.arrive)) {
            return false;
        }
        if (!Objects.equals(this.type_reservation, other.type_reservation)) {
            return false;
        }
        return true;
    }
    
    
    
}
