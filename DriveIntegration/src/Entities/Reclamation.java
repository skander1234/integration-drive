/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class Reclamation {
     private int id_rec;
    private String sujet_rec;
    private String msg;
    private int etat;
    private Timestamp dateAjout;
    private Client client;

    public Reclamation(Client c,int id_rec, String sujet_rec, String msg, int etat, Timestamp dateAjout) {
        this.client =c;
        this.id_rec = id_rec;
        this.sujet_rec = sujet_rec;
        this.msg = msg;
        this.etat = etat;
        this.dateAjout = dateAjout;
    }

    public Reclamation() {
    }

    public Reclamation(int i, int i0, int i1, String mnayek, int i2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_rec() {
        return id_rec;
    }

    public Client getClient() {
        return client;
    }   
    public String getSujet_rec() {
        return sujet_rec;
    }

    public String getMsg() {
        return msg;
    }

    public int getEtat() {
        return etat;
    }

    public Timestamp getDateAjout() {
        return dateAjout;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public void setSujet_rec(String sujet_rec) {
        this.sujet_rec = sujet_rec;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
public void setClient(Client client) {
        this.client=client;
    }
    public void setDateAjout(Timestamp dateAjout) {
        this.dateAjout = dateAjout;
    }

    @Override
    public String toString() {
        return "ReclamationC{" + "id_rec=" + id_rec + ", sujet_rec=" + sujet_rec + ", msg=" + msg + ", etat=" + etat + ", dateAjout=" + dateAjout + ", " +client.toString() + '}';
    }
    
}
