/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author Belgaroui Ghazi
 */
public class Chauffeur extends User {
    private String adresse;
    private int cin;
    private String permis;
    private String nom;
    private String prenom;

    public Chauffeur() {
    }

    
    public Chauffeur(User user, String adresse, int cin, String permis, String nom, String prenom) {
        super(user.getId_user(),user.getN_tel(),user.getLogin(),user.getMdp(),user.getEtat(),user.getMail());
        this.adresse = adresse;
        this.cin = cin;
        this.permis = permis;
        this.nom = nom;
        this.prenom = prenom;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
     public String getAdresse() {
        return adresse;
    }

    public int getCin() {
        return cin;
    }

    public String getPermis() {
        return permis;
    }
public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        String u=super.toString();
        return "Chauffeur{"+ u + ", adresse=" + adresse + ", cin=" + cin + ", permis=" + permis + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

   
   
    

 
    
}
