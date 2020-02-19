/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import Entities.Event;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Dimassi Abdelhak
 */
public class PDFutil {
    Connection cn2;
    Statement ste;
    
    public PDFutil() {
        cn2 = DataSource.getInstance().getConnexion();
    }
    public void listEvent() throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        ste=cn2.createStatement();
        ResultSet rs=ste.executeQuery("SELECT * FROM event");
        PdfWriter.getInstance(doc, new FileOutputStream("./Liste Activité.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste des activités:  "));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        /*cell = new PdfPCell(new Phrase("ID_Activite",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.RED);
        table.addCell(cell);*/
        
        cell = new PdfPCell(new Phrase("nom",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("nbr_place",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
        
          cell = new PdfPCell(new Phrase("depart",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
          cell = new PdfPCell(new Phrase("arrivee",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell); 
        
          cell = new PdfPCell(new Phrase("date_allee",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("date_retour",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase("Description",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
        /*cell = new PdfPCell(new Phrase("IDenfants",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.yellow);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("IDanimateurs",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.yellow);
        table.addCell(cell);*/
        
     while (rs.next()) {                
            
               Event e = new Event();
            //    a.setId(rs.getInt(1));
                   e.setNom(rs.getString(2));
               e.setNbr_place(rs.getInt(3));
                e.setDepart(rs.getString(4));
                e.setArrivee(rs.getString(5));
                e.setDate_allee(rs.getTimestamp(6));
                e.setDate_retour(rs.getTimestamp(7));
                e.setDescription(rs.getString(8));
              
               /*cell = new PdfPCell(new Phrase(String.valueOf(a.getId()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);*/
               
               cell = new PdfPCell(new Phrase(e.getNom(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);
               
              
               
         cell = new PdfPCell(new Phrase(e.getDepart(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);
        
               cell = new PdfPCell(new Phrase(e.getArrivee(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);
               
                cell = new PdfPCell(new Phrase(e.getDescription(),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);
               
              
               
                        }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("./Liste Activité.pdf"));
            }
}