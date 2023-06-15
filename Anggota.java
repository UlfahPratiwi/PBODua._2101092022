/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ulfah.model;

/**
 *
 * @author ASUS
 */
public class Anggota {
    private String kodeanggota;
    private String namaanggota;
    private String alamat;
    private String jeniskelamin;
    
    //contruktor
    
    public Anggota() {
        
    }
    
    public Anggota(String kodeanggota, String namaanggota, String alamat, String jeniskelamin) {
        this.kodeanggota = kodeanggota;
        this.namaanggota = namaanggota;
        this.alamat = alamat;
        this.jeniskelamin = jeniskelamin;
    }
    
   
    //acessor

    public String getKodeanggota() {
        return kodeanggota;
    }

    public void setKodeanggota(String kodeanggota) {
        this.kodeanggota = kodeanggota;
    }

    public String getNamaanggota() {
        return namaanggota;
    }

    public void setNamaanggota(String namaanggota) {
        this.namaanggota = namaanggota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }
    
    

    

   
    
   
}
