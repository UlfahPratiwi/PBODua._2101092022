/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbodua_2101092022;

import Ulfah.dao.Koneksi;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class PBODua_2101092022 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Koneksi koneksi = new Koneksi ();
            Connection con = koneksi.getKoneksi();
            JOptionPane.showMessageDialog(null,"koneksi OK");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PBODua_2101092022.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PBODua_2101092022.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
