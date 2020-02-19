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
import Entities.Location;
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
    public void listLocation() throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        ste=cn2.createStatement();
        ResultSet rs=ste.executeQuery("SELECT date_d,date_f,prix FROM location ");
        PdfWriter.getInstance(doc, new FileOutputStream("./Locationn.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Location:  "));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        /*cell = new PdfPCell(new Phrase("ID_Activite",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.RED);
        table.addCell(cell);*/
        
        cell = new PdfPCell(new Phrase("Date debut de la location",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Date fin de la location",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.orange);
        table.addCell(cell);
        
        
          cell = new PdfPCell(new Phrase("Prix de la location",FontFactory.getFont("Comic Sans MS",12)));
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
            
               Location p = new Location();
            //    a.setId(rs.getInt(1));
                   
                p.setDate_d(rs.getTimestamp(1));
                p.setDate_f(rs.getTimestamp(2));
                p.setPrix(rs.getFloat(3));
              
               /*cell = new PdfPCell(new Phrase(String.valueOf(a.getId()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);*/
               
               cell = new PdfPCell(new Phrase(String.valueOf(p.getDate_d()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);
               
              
               
                 cell = new PdfPCell(new Phrase(String.valueOf(p.getDate_f()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);
        
               cell = new PdfPCell(new Phrase(String.valueOf(p.getPrix()),FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(Color.pink);
               table.addCell(cell);
               
                
               
              
               
                        }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("./Locationn.pdf"));
            }
}