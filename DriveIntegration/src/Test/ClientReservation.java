/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Core.*;
import Utils.Criteres;
import Utils.DataSource;
import Utils.Interval;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Armand
 */
public class ClientReservation {
    public static void startConsole() throws ParseException{
    DataSource ds =  DataSource.getInstance();
    
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date parsedDate = dateFormat.parse("2021-02-09 11:20:21");
    java.util.Date parsedDate2 = dateFormat.parse("2021-02-09 12:55:05");
    Timestamp timestamp1=new java.sql.Timestamp(parsedDate.getTime());
    Timestamp timestamp2=new java.sql.Timestamp(parsedDate2.getTime());
    
    UserC us=new UserC();
    ClientC cs= new ClientC();
    ReservationC rs= new ReservationC();
    
    rs.RechercheAvance("2019").stream().forEach(System.out::println);
    /*
        Interval i = new Interval();
        i.ajouter("heure", "2018-01-01", "2019-12-30");
        rs.filtrerParInterval(i).stream().forEach(System.out::println);*/
        
      /*  Criteres c=new Criteres();
        c.ajouterCritere("depart", "tunis");
        c.ajouterCritere("id_reservation",18);
        c.ajouterCritere("id_reservation",24);
        c.ajouterCritere("code_liv",12);
    rs.filterSelonDesCritere(c).stream().forEach(System.out::println);*/
    
//rs.trier("asc","id_reservation").stream().forEach(System.out::println);
    //rs.afficher().stream().forEach(System.out::println);
    //cs.afficher().stream().forEach(System.out::println);
    //us.afficher().stream().forEach(System.out::println);
    
}
}
