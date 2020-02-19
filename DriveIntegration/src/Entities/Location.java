/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Belgaroui Ghazi
 */
public class Location {
    private int id_location;
    private int id_client;
    private int id_velo;
    private Timestamp date_d;
    private Timestamp date_f;
    private float prix;
    

    public Location() {
    }

    public Location(int id_client, int id_velo, Timestamp date_d, Timestamp date_f, float prix) {
        
        this.id_client = id_client;
        this.id_velo = id_velo;
        this.date_d = date_d;
        this.date_f = date_f;
        this.prix = prix;
    }

    public int getId_location() {
        return id_location;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_velo() {
        return id_velo;
    }

    public Timestamp getDate_d() {
        return date_d;
    }

    public Timestamp getDate_f() {
        return date_f;
    }

    public float getPrix() {
        return prix;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_velo(int id_velo) {
        this.id_velo = id_velo;
    }

    public void setDate_d(Timestamp date_d) {
        this.date_d = date_d;
    }

    public void setDate_f(Timestamp date_f) {
        this.date_f = date_f;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "location{" + "id_location=" + id_location + ", id_client=" + id_client + ", id_velo=" + id_velo + ", date_d=" + date_d + ", date_f=" + date_f + ", prix=" + prix + '}';
    }
    
    
    
}
