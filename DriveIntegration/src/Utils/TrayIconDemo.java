package Utils;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class TrayIconDemo {


    public void trayAjout() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("./src/icon.png.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray ajout");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Location ajoutée");
        tray.add(trayIcon);

        trayIcon.displayMessage("Location", "Location ajoutée ", MessageType.INFO);
    }
}