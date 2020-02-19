/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Armand
 */
public class User {
    protected int id_user;
    protected int n_tel;
    protected String login;
    protected String mdp;
    protected int etat;
    protected String mail;

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", n_tel=" + n_tel + ", login=" + login + ", mdp=" + mdp + ", etat=" + etat + ", mail=" + mail + '}';
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getN_tel() {
        return n_tel;
    }

    public void setN_tel(int n_tel) {
        this.n_tel = n_tel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User(int n_tel, String login, String mdp, int etat, String mail) {
        this.n_tel = n_tel;
        this.login = login;
        this.mdp = mdp;
        this.etat = etat;
        this.mail = mail;
    }

    public User(int id,int n_tel, String login, String mdp, int etat, String mail) {
        this.id_user=id;
        this.n_tel = n_tel;
        this.login = login;
        this.mdp = mdp;
        this.etat = etat;
        this.mail = mail;
    }
    
    public User() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }


}
