/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulfah.controller;

import Ulfah.dao.AnggotaDao;
import Ulfah.dao.AnggotaDaoImpl;
import Ulfah.dao.BukuDao;
import Ulfah.dao.BukuDaoImpl;
import Ulfah.dao.Koneksi;
import Ulfah.dao.PeminjamanDao;
import Ulfah.dao.PeminjamanDaoImpl;
import Ulfah.dao.PengembalianDao;
import Ulfah.dao.PengembalianDaoImpl;
import Ulfah.model.Anggota;
import Ulfah.model.Peminjaman;
import Ulfah.model.Pengembalian;
import Ulfah.view.FormPengembalian;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class PengembalianController {
     FormPengembalian formPengembalian;
     AnggotaDao anggotaDao;
     BukuDao bukuDao;
     PeminjamanDao peminjamanDao;
     PengembalianDao pengembalianDao;
     Pengembalian pengembalian;
     Connection con;
    
    public PengembalianController(FormPengembalian formPengembalian){
         try {
             this.formPengembalian = formPengembalian;
             anggotaDao = new AnggotaDaoImpl();
             bukuDao = new BukuDaoImpl();
             peminjamanDao = new PeminjamanDaoImpl();
             pengembalianDao = new PengembalianDaoImpl();
             Koneksi k = new Koneksi();
             con = k.getKoneksi();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void clearForm(){
        formPengembalian.getTxtTglpinjam().setText("");
        formPengembalian.getTxtTglkembali().setText("");
        formPengembalian.getTxtTgldikembalikan().setText("");
        formPengembalian.getTxtTerlambat().setText("");
        formPengembalian.getTxtDenda().setText("");
        formPengembalian.getTxtKodeanggota().setText("");
        formPengembalian.getTxtKodebuku().setText("");
    }
    
    public void tampil(){
         try {
             DefaultTableModel tabel = (DefaultTableModel) formPengembalian.getTblPengembalian().getModel();
             tabel.setRowCount(0);
             List<Pengembalian> list = pengembalianDao.getAllPengembalian(con);
             for(Pengembalian p : list){
                 Anggota anggota = anggotaDao.getAnggota(con, p.getKodeanggota());
                 Peminjaman pinjam = peminjamanDao.getPeminjaman(con, p.getKodeanggota(), p.getKodebuku(), p.getTglpinjam());
                 Object[] row = {
                     p.getKodeanggota(),
                     anggota.getNamaanggota(),
                     p.getKodebuku(),
                     pinjam.getTglpinjam(),
                     pinjam.getTglkembali(),
                     p.getTgldikembalikan(),
                     p.getTerlambat(),
                     p.getDenda()
                 };
                 tabel.addRow(row);
             } } catch (Exception ex) {
             Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    public void getPengembalian(){
         try {
             String kodeanggota = formPengembalian.getTblPengembalian().getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 0).toString();
             String kodebuku = formPengembalian.getTblPengembalian().getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 2).toString();
             String tglpinjam = formPengembalian.getTblPengembalian().getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 3).toString();
             pengembalian = new Pengembalian();
             Peminjaman peminjaman = peminjamanDao.getPeminjaman(con, kodeanggota, kodebuku, tglpinjam);
             int terlambat = pengembalianDao.selisihTanggal(con, pengembalian.getTgldikembalikan(),peminjaman.getTglkembali());
             pengembalian.setTerlambat(terlambat);
             double denda = pengembalian.getDenda();
             formPengembalian.getTxtKodeanggota().setText(kodeanggota);
             formPengembalian.getTxtKodebuku().setText(kodebuku);
             formPengembalian.getTxtTglpinjam().setText(tglpinjam);
             formPengembalian.getTxtTglkembali().setText(peminjaman.getTglkembali());
             formPengembalian.getTxtTgldikembalikan().setText(pengembalian.getTgldikembalikan());
             formPengembalian.getTxtTerlambat().setText(terlambat+"");
             formPengembalian.getTxtDenda().setText(denda+"");
         } catch (Exception ex) {
             Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }
}
