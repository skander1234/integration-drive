    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;





/**
 *
 * @author Meriem
 */
public class Event {
    
    
 private int id_event;
 private String nom;
private  int nbr_place;
 private String depart;
 private String arrivee;
 private Timestamp date_allee;
 private Timestamp date_retour;
 private String description;
 
    public Event() {
    }

    public Event( String nom,int nbr_place, String depart, String arrivee, Timestamp date_allee, Timestamp date_retour, String description) {
        this.nom = nom;
        this.nbr_place = nbr_place;
        this.depart = depart;
        this.arrivee = arrivee;
        this.date_allee = date_allee;
        this.date_retour = date_retour;
        this.description = description;
    }

    public Event(String nom, int nbr_place, String depart, Timestamp date_allee, Timestamp date_retour, String description) {
        this.nom = nom;
        this.nbr_place = nbr_place;
        this.depart = depart;
        this.date_allee = date_allee;
        this.date_retour = date_retour;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" + "id_event=" + id_event + ", nom=" + nom + ", nbr_place=" + nbr_place + ", depart=" + depart + ", arrivee=" + arrivee + ", date_allee=" + date_allee + ", date_retour=" + date_retour + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Event other = (Event) obj;
        if (this.id_event != other.id_event) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public Date getDate_allee() {
        return date_allee;
    }

    public void setDate_allee(Timestamp date_allee) {
        this.date_allee = date_allee;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Timestamp date_retour) {
        this.date_retour = date_retour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  

}
