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
public class Client extends User{
    private int nbr_res_annulee;


    public Client(User u,int nbr_res_annulee) {
        super(u.getN_tel(), u.getLogin(), u.getMdp(), u.getEtat(), u.getMail());
        this.id_user=u.getId_user();
        this.nbr_res_annulee = nbr_res_annulee;
    }
    public Client(int nbr_res_annulee) {
        this.nbr_res_annulee = nbr_res_annulee;
    }

        public Client() {
    }

    public int getNbr_res_annulee() {
        return nbr_res_annulee;
    }

    public void setNbr_res_annulee(int nbr_res_annulee) {
        this.nbr_res_annulee = nbr_res_annulee;
    }
        
    @Override
    public String toString() {
        String user= super.toString();
        return "Client{" + user + "et " + "nbr_res_annulee=" + nbr_res_annulee + '}';
    }

    public String getId_chauffeur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
