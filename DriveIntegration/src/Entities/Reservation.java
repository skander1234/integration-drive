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
    private int id_client;
    private int id_chauffeur;
    private String depart;
    private String arrive;
    private Timestamp heure;
    private double prix;
    private String type_reservation;
    private int code_liv;
    private int nbr_place;

    public Reservation() {
    }

    public Reservation(int id_client, int id_chauffeur, String depart, String arrive, Timestamp heure, double prix, String type_reservation, int code_liv, int nbr_place) {
        this.id_client = id_client;
        this.id_chauffeur = id_chauffeur;
        this.depart = depart;
        this.arrive = arrive;
        this.heure = heure;
        this.prix = prix;
        this.type_reservation = type_reservation;
        this.code_liv = code_liv;
        this.nbr_place = nbr_place;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
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
        return "Reservation{" + "id_reservation=" + id_reservation + ", id_client=" + id_client + ", id_chauffeur=" + id_chauffeur + ", depart=" + depart + ", arrive=" + arrive + ", heure=" + heure + ", prix=" + prix + ", type_reservation=" + type_reservation + ", code_liv=" + code_liv + ", nbr_place=" + nbr_place + '}';
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
        if (this.id_client != other.id_client) {
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
